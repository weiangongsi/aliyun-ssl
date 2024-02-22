package com.dcssn.ali.ssl.common.exception;

/**
 * <p>
 * 基础异常类
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public class ProjectException extends RuntimeException {

    private static final long serialVersionUID = -8531807646969666355L;

    private Throwable cause;
    private ExpResultMessage result;

    public ProjectException(ExpResultMessage result, Throwable cause) {
        super(String.format("%s,系统异常源信息:%s", result.toString(), cause.getMessage()), cause);
        this.result = result;
        this.cause = cause;
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
    }

    /**
     * @param result 异常实体对象
     */
    public ProjectException(ExpResultMessage result) {
        super(result.toString());
        this.result = result;
    }

    /**
     * @param message 异常描述
     */
    public ProjectException(String message) {
        super(message);
    }

    /**
     * @param result  异常实体对象
     * @param message 描述
     */
    public ProjectException(ExpResultMessage result, String message) {
        super(message);
        this.result = new ExpResultMessage() {
            @Override
            public int status() {
                return result.status();
            }

            @Override
            public int code() {
                return result.code();
            }

            @Override
            public String message() {
                return message;
            }
        };
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public ExpResultMessage getResult() {
        return result;
    }
}