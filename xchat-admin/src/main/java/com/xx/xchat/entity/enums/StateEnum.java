package com.xx.xchat.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum StateEnum implements IEnum<Integer> {

    NORMAL(1, "正常"),

    BAN(2, "禁用");

    private final Integer value;
    private final String desc;

    StateEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue     // 表示在反序列化的时候只处理这个字段，MP必须需要这个配置
    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static StateEnum parseValue(Integer value) {
        return Stream.of(StateEnum.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
