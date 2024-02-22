package com.dcssn.ali.ssl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "certificate")
public class Certificate {

    /**
     * 资源 ID
     */
    @Id
    @Column(name = "instance_id")
    private String instanceId;

    /**
     * 证书类型
     */
    @Column(name = "cert_type")
    private String certType;

    /**
     * 证书名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 域名
     */
    @Column(name = "domain")
    private String domain;

    /**
     * 证书绑定的所有域名。多个域名之间使用半角逗号（,）分隔
     */
    @Column(name = "sans")
    private String sans;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     *
     * 证书 ID
     */
    @Column(name = "certificate_id")
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
    @Column(name = "status")
    private String status;

    /**
     * 证书开始时间
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * 证书结束时间
     */
    @Column(name = "end_date")
    private LocalDate endDate;


}
