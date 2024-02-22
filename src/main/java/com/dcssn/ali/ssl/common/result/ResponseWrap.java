package com.dcssn.ali.ssl.common.result;


import com.dcssn.ali.ssl.common.exception.ExpResultMessage;
import com.dcssn.ali.ssl.common.exception.ProjectException;
import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;

/**
 * <p>
 * 返回值包装
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public class ResponseWrap {

    public static <T> HttpResult<T> success(T obj) {
        return new HttpResult<T>().success(obj);
    }

    public static <T> HttpResult<T> success() {
        return new HttpResult<T>().success();
    }

    public static <T> HttpResult<T> success(String message) {
        HttpResult<T> httpResult = new HttpResult<T>().success();
        httpResult.setMessage(message);
        return httpResult;
    }

    public static <T> HttpResult<T> fail(ExpResultMessage resultMessage) {
        return new HttpResult<T>().fail(resultMessage);
    }

    public static <T> HttpResult<T> fail(int code, String message) {
        return new HttpResult<T>().fail(code, message);
    }

    public static <T> T handler(HttpResult<T> httpResult) {
        if (httpResult == null || httpResult.getStatus() != HttpResultStatusEnum.SUCCESS.status) {
            if (httpResult != null) {
                throw new ProjectException(new ExpResultMessage() {
                    @Override
                    public int status() {
                        return httpResult.getStatus();
                    }

                    @Override
                    public int code() {
                        return httpResult.getCode();
                    }

                    @Override
                    public String message() {
                        return httpResult.getMessage();
                    }
                });
            }
            throw new ProjectException(ProjectResultMessage.FAIL);
        }
        return httpResult.getData();
    }
}
