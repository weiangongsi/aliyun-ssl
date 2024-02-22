package com.dcssn.ali.ssl.common.exception;


import com.dcssn.ali.ssl.common.result.HttpResultStatusEnum;

/**
 * <p>
 * 异常返回错误信息实现
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public enum ProjectResultMessage implements ExpResultMessage {
    SUCCESS(HttpResultStatusEnum.SUCCESS.status, 0, "成功"),
    CODE_001(HttpResultStatusEnum.FAILURE.status, 1, "连接服务器失败，请检查服务器连接信息"),
    CODE_002(HttpResultStatusEnum.FAILURE.status, 2, "用户名或密码错误"),
    CODE_400(HttpResultStatusEnum.FAILURE.status, 400, "参数错误"),
    CODE_401(HttpResultStatusEnum.FAILURE.status, 401, "权限错误"),
    FAIL(HttpResultStatusEnum.FAILURE.status, 999, "系统异常");
    public final int status;
    public final int code;
    public final String message;

    ProjectResultMessage(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public int status() {
        return this.status;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
