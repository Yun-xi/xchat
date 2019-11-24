package com.xx.xchat.exception;

import com.xx.xchat.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-31 17:31
 */
@Getter
public class XException extends RuntimeException{
    /** 错误编码 **/
    private Integer errCode;
    /** 错误信息 **/
    private String errMessage;
    /** 错误描述 **/
    private String description;

    /**
     * 默认异常
     */
    public XException() {
        this(ErrorEnum.SYSTEM_ERROR);
    }

    /**
     * @param errorEnum 错误枚举
     */
    public XException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMessage());
        this.errCode = errorEnum.getErrCode();
        this.errMessage = errorEnum.getErrMessage();
    }

    /**
     * @param errorEnum   错误枚举
     * @param description 错误详细内容
     */
    public XException(ErrorEnum errorEnum, String description) {
        super(description);
        this.description = description;
        this.errCode = errorEnum.getErrCode();
        this.errMessage = errorEnum.getErrMessage();
    }
}
