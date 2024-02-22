package com.dcssn.ali.ssl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 证书部署实体
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Data
@Entity
@Table(name = "certificate_deploy")
public class CertificateDeploy {

    /**
     * 域名
     */
    @Id
    @Column(name = "domain")
    private String domain;

    /**
     * 定时任务状态
     * ON：开启
     * OFF：关闭
     */
    @Column(name = "cron_status")
    private String cronStatus;

    /**
     * 证书部署时间
     */
    @Column(name = "deploy_date")
    private LocalDate deployDate;

    /**
     * 证书部署状态
     */
    @Column(name = "deploy_status")
    private String deployStatus;

    /**
     * 证书部署失败原因
     */
    @Column(name = "deploy_error")
    private String deployError;


    /**
     * 下次证书部署时间
     */
    @Column(name = "next_deploy_date")
    private LocalDate nextDeployDate;

    /**
     * 部署的主机
     */
    @Column(name = "host")
    private String host;

    /**
     * 部署的主机端口
     */
    @Column(name = "port")
    private Integer port;

    /**
     * 部署的主机用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 部署的主机密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 部署的主机证书路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 部署的后要执行的命令，重启nginx
     */
    @Column(name = "shell")
    private String shell;


}
