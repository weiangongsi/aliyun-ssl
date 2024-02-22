package com.dcssn.ali.ssl.vo.certificate.deploy;

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
public class CertificateDeployAddReq {

    /**
     * 域名
     */
    private String domain;

    /**
     * 定时任务状态
     * ON：开启
     * OFF：关闭
     */
    private String cronStatus;

    /**
     * 部署的主机
     */
    private String host;

    /**
     * 部署的主机端口
     */
    private Integer port;

    /**
     * 部署的主机用户名
     */
    private String username;

    /**
     * 部署的主机密码
     */
    private String password;

    /**
     * 部署的主机证书路径
     */
    private String path;

    /**
     * 部署的后要执行的命令，重启nginx
     */
    private String shell;


}
