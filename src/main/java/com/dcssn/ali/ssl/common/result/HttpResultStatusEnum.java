package com.dcssn.ali.ssl.common.result;

/**
 * <p>
 * 所有工程接口返回状态常量
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public enum HttpResultStatusEnum {
    SUCCESS(1, "成功"),

    FAILURE(-1, "失败"),

    PROCESSING(-2, "未决");

    public final int status;
    public final String des;


    HttpResultStatusEnum(int status, String des) {
        this.status = status;
        this.des = des;
    }
}

