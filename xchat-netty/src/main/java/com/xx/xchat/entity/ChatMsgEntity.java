package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.entity.enums.MsgSignFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:28
 */
@Data
@Accessors(chain = true)
@TableName("n_friends_request")
@ApiModel("用户加好友请求")
public class ChatMsgEntity extends BaseEntity {

    @ApiModelProperty("发送方用户编号")
    private String sendUserId;

    @ApiModelProperty("接收方用户编号")
    private String acceptUserId;

    @ApiModelProperty("消息")
    private String msg;

    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private MsgSignFlagEnum signFlag;
}
