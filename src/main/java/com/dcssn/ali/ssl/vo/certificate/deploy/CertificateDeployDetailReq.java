package com.dcssn.ali.ssl.vo.certificate.deploy;

import lombok.Data;

/**
 * <p>
 * 证书部署详情查询
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Data
public class CertificateDeployDetailReq {

    /**
     * 域名
     */
    private String domain;

}
