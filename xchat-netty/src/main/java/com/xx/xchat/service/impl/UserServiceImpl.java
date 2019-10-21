package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.ChatMsgMapper;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.ChatMsgEntity;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.entity.enums.MsgSignFlagEnum;
import com.xx.xchat.enums.ErrorEnum;
import com.xx.xchat.exception.XException;
import com.xx.xchat.netty.domain.ChatMsg;
import com.xx.xchat.service.ChatMsgService;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.MD5Util;
import com.xx.xchat.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private ChatMsgService chatMsgService;
    @Autowired
    private ChatMsgMapper chatMsgMapper;
    @Autowired
    private ChatMsgService chatMsgService;

    @Override
    public String loginOrRegister(String username, String password, String cid) throws XException {

        UserEntity userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
        boolean usernameIsExist = (null != userEntity);

        if (usernameIsExist) {
            // 登录
            String targetPassword = userEntity.getPassword();
            String salt = userEntity.getSalt();

            String currentPassword = MD5Util.encrypt(password + salt);
            if (!currentPassword.equals(targetPassword)) {
                throw new XException(ErrorEnum.PASSWORD_IS_ERROR, "用户名或密码错误");
            }
        } else {
            // 注册
            String salt = UUID.randomUUID().toString();
            String md5Password = MD5Util.encrypt(password + salt);

            userEntity = UserEntity.builder().username(username).password(md5Password).salt(salt).cid(cid).build();
            boolean registerResult = this.save(userEntity);
            if (registerResult) {
                userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
            } else {
                throw new XException(ErrorEnum.REGISTER_FILED, "注册失败");
            }
        }

        return userEntity.getId();
    }

    @Override
    @Transactional
    public String saveMsg(ChatMsg chatMsg) {
        String primaryId = SnowflakeIdWorker.getStringId();
        ChatMsgEntity chatMsgEntity =
                new ChatMsgEntity().setSendUserId(chatMsg.getSenderId()).setAcceptUserId(chatMsg.getReceiverId()).setMsg(chatMsg.getMsg()).setSignFlag(MsgSignFlagEnum.NOSIGN);
        chatMsgEntity.setId(primaryId);

        chatMsgService.save(chatMsgEntity);
        return primaryId;
    }

    @Override
    public void updateMsgSigned(List<String> msgIdList) {
        List<ChatMsgEntity> chatMsgEntityList = chatMsgMapper.selectBatchIds(msgIdList);
        chatMsgEntityList.stream().forEach(chatMsgEntity -> chatMsgEntity.setSignFlag(MsgSignFlagEnum.ALREADY));

        chatMsgService.updateBatchById(chatMsgEntityList);
    }

}
