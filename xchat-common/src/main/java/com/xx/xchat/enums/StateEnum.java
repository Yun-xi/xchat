package com.xx.xchat.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public enum StateEnum implements IEnum {

    NORMAL(1, "正常"),

    BAN(2, "禁用");

    private final int value;
    private final String desc;

    StateEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static StateEnum parseValue(Integer v) {
        if (v == null) {
            return null;
        }
        for (StateEnum e : StateEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
