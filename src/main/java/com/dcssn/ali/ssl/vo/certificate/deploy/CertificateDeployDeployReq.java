package com.dcssn.ali.ssl.vo.certificate.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 证书部署
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDeployDeployReq {

    /**
     * 域名
     */
    private String domain;

}
