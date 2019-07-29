package com.xx.xchat.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum MenuTypeEnum implements IEnum {

    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private final int value;
    private final String desc;

    MenuTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MenuTypeEnum parseValue(Integer v) {
        if (v == null) {
            return null;
        }
        for (MenuTypeEnum e : MenuTypeEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Serializable getValue() {
        return null;
    }
}
