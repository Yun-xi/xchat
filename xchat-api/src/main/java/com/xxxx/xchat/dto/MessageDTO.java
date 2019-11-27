package com.xxxx.xchat.dto;

import com.xxxx.xchat.enums.MessageSignFlagEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:28
 */
@Data
@Accessors(chain = true)
public class MessageDTO{

    private String sendUserId;

    private String acceptUserId;

    private String msg;

    private MessageSignFlagEnum signFlag;
}
