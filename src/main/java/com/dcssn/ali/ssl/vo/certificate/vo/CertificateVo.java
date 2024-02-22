package com.dcssn.ali.ssl.vo.certificate.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 证书实体
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Data
public class CertificateVo {

    /**
     * 资源 ID
     */
    private String instanceId;

    /**
     * 证书类型
     */
    private String certType;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

    /**
     * 证书绑定的所有域名。多个域名之间使用半角逗号（,）分隔
     */
    private String sans;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 证书 ID
     */
    private Long certificateId;

    /**
     * 订单或者证书状态，当入参 OrderType=CPACK 或者 BUY 时返回。
     * <p>
     * PAYED：待申请。
     * CHECKING：审核中。
     * CHECKED_FAIL：审核失败。
     * ISSUED：已签发。
     * WILLEXPIRED：即将过期。
     * EXPIRED：已过期。
     * NOTACTIVATED：未激活。
     * REVOKED：吊销完成。
     */
    private String status;

    /**
     * 证书开始时间
     */
    private LocalDate startDate;

    /**
     * 证书结束时间
     */
    private LocalDate endDate;


    /**
     * 定时任务状态
     * ON：开启
     * OFF：关闭
     */
    private String cronStatus;

    /**
     * 证书部署时间
     */
    private LocalDate deployDate;

    /**
     * 证书部署状态
     */
    private String deployStatus;

    /**
     * 证书部署失败原因
     */
    private String deployError;

    /**
     * 下次证书部署时间
     */
    private LocalDate nextDeployDate;

}
