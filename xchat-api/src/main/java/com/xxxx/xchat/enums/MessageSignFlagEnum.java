package com.xxxx.xchat.enums;


import java.util.stream.Stream;

public enum MessageSignFlagEnum {

    NOSIGN(0, "未签收"),

    ALREADY(1, "已签收");

    private final Integer value;
    private final String desc;

    MessageSignFlagEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static MessageSignFlagEnum parseValue(Integer value) {
        return Stream.of(MessageSignFlagEnum.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
