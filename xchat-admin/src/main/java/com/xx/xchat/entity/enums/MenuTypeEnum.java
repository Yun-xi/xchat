package com.xx.xchat.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum MenuTypeEnum implements IEnum<Integer> {

    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private final Integer value;
    private final String desc;

    MenuTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static MenuTypeEnum parseValue(Integer value) {
        return Stream.of(MenuTypeEnum.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

}
