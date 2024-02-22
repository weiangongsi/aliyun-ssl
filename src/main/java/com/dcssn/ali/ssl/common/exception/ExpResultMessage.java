package com.dcssn.ali.ssl.common.exception;

/**
 * <p>
 * 请求返回体
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public interface ExpResultMessage {

    /**
     * 接口响应状态码
     * 1：成功、-1：失败、-2：未知（未决）
     */
    int status();
    /**
     * 接口响应结果码（错误码）端口号+3位错误码
     */
    int code();

    /**
     * 接口响应结果码描述
     */
    String message();

}
