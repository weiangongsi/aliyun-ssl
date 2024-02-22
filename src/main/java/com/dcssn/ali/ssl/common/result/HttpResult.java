package com.dcssn.ali.ssl.common.result;

import com.dcssn.ali.ssl.common.exception.ExpResultMessage;
import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;
import lombok.Data;

/**
 * <p>
 * 请求返回体
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Data
public class HttpResult<T> {

    /**
     * 接口响应状态码
     * 1：成功、-1：失败、-2：未知（未决）
     */
    private int status;

    /**
     * 接口响应结果码（错误码）
     */
    private int code;

    /**
     * 接口响应结果码描述
     */
    private String message;

    /**
     * 接口响应数据
     */
    private T data;


    public HttpResult<T> status(int status) {
        this.status = status;
        return this;
    }


    public HttpResult<T> code(int code) {
        this.code = code;
        return this;
    }

    public HttpResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public HttpResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public HttpResult<T> success() {
        return this.buildByExpResultMessage(ProjectResultMessage.SUCCESS);
    }

    public HttpResult<T> success(T data) {
        this.buildByExpResultMessage(ProjectResultMessage.SUCCESS);
        this.setData(data);
        return this;
    }


    public HttpResult<T> success(ExpResultMessage resultMessage, T data) {
        this.buildByExpResultMessage(resultMessage);
        this.setData(data);
        return this;
    }

    public HttpResult<T> fail(ExpResultMessage resultMessage) {
        return this.buildByExpResultMessage(resultMessage);
    }

    public HttpResult<T> fail(ExpResultMessage resultMessage, T data) {
        this.buildByExpResultMessage(resultMessage);
        this.setData(data);
        return this;
    }

    public HttpResult<T> fail(int code, String message) {
        this.setStatus(HttpResultStatusEnum.FAILURE.status);
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public HttpResult<T> buildByExpResultMessage(ExpResultMessage resultMessage) {
        this.setStatus(resultMessage.status());
        this.setCode(resultMessage.code());
        this.setMessage(resultMessage.message());
        return this;
    }
}
