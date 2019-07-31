package com.xx.xchat.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.stream.Stream;

@Getter
public enum MenuTypeEnum implements IEnum {

    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private final Integer value;
    private final String desc;

    MenuTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MenuTypeEnum parseValue(Integer value) {
        return Stream.of(MenuTypeEnum.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

}
