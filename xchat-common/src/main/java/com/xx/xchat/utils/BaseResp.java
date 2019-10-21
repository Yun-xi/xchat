package com.xx.xchat.utils;

import lombok.*;

/**
 * 返回消息
 */
@Data
@Builder
public class BaseResp<T> {
    private Integer retCode;
    private String retMsg;
    private T data;

    /**
     * ok
     *
     * @param data 响应内容
     * @param <T>  响应内容类型
     * @return
     */
    public static <T> BaseResp<T> ok(T data) {
        return BaseResp.<T>builder()
                .data(data)
                .retCode(Constants.RetCode.SUCCESS)
                .retMsg("操作成功")
                .build();
    }

    /**
     * ok
     *
     * @return
     */
    public static BaseResp ok() {
        return ok(null);
    }

    /**
     * fail
     *
     * @param failCode 错误编码
     * @param failMsg  错误消息
     */
    public static BaseResp fail(Integer failCode, String failMsg) {
        return BaseResp.builder()
                .retCode(failCode)
                .retMsg(failMsg)
                .build();
    }

    /**
     * fail
     *
     * @param failMsg  错误消息
     */
    public static BaseResp fail(String failMsg) {
        return BaseResp.builder()
                .retCode(Constants.RetCode.FAIL)
                .retMsg(failMsg)
                .build();
    }

    /**
     * fail
     *
     * @param data 响应内容
     * @param <T>  响应内容类型
     */
    public static <T> BaseResp<T> fail(T data) {
        return BaseResp.<T>builder()
                .retCode(Constants.RetCode.FAIL)
                .retMsg("操作失败")
                .data(data)
                .build();
    }

    /**
     * fail
     */
    public static BaseResp fail() {
        return fail(null);
    }

}
