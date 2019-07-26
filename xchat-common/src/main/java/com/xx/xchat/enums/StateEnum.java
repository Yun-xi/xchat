package com.xx.xchat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 禁用
     */
    BAN(2);

    private int value;

}
