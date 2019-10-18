package com.xx.xchat.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ErrorEnum {

    SYSTEM_ERROR(1000, "系统异常"),
    ARGS_IS_EMPTY(1001, "参数为空"),
    PASSWORD_IS_ERROR(2000, "参数为空");

    private final Integer errCode;
    private final String errMessage;

    ErrorEnum(Integer errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public static ErrorEnum fromValue(Integer value) {
        return Stream.of(ErrorEnum.values())
                .filter(val -> val.getErrCode().equals(value))
                .findFirst()
                .orElse(null);
    }
}
