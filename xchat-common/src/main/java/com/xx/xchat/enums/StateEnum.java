package com.xx.xchat.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StateEnum implements IEnum {

    NORMAL(1, "正常"),

    BAN(2, "禁用");

    private final Integer value;
    private final String desc;

    StateEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static StateEnum parseValue(Integer value) {
        return Stream.of(StateEnum.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
