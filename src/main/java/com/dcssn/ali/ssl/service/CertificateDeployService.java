package com.dcssn.ali.ssl.service;

import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployAddReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDeployReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDetailReq;

/**
 * <p>
 * 证书部署service
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public interface CertificateDeployService {

    void add(CertificateDeployAddReq req);

    CertificateDeploy detail(CertificateDeployDetailReq req);

    void deploy(CertificateDeployDeployReq req);

}
