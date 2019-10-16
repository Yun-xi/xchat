package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.ChatMsgMapper;
import com.xx.xchat.entity.ChatMsgEntity;
import com.xx.xchat.service.ChatMsgService;
import org.springframework.stereotype.Service;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:46
 */
@Service
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsgEntity> implements ChatMsgService {
}
