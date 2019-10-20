package com.xx.xchat.netty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-10-20 23:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataContent implements Serializable {

    private static final long serialVersionUID = -3482676847449113190L;

    private Integer action;     // 动作类型

    private ChatMsg chatMsg;    // 用户的聊天内容

    private String extand;      // 扩展字段
}
