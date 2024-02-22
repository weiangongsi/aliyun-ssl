package com.dcssn.ali.ssl.controller;

import com.dcssn.ali.ssl.common.result.HttpResult;
import com.dcssn.ali.ssl.common.result.ResponseWrap;
import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.service.CertificateDeployService;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployAddReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDeployReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDetailReq;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 证书部署
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@RestController
@RequestMapping("certificate/deploy")
public class CertificateDeployController {

    @Resource
    private CertificateDeployService certificateDeployService;

    @PostMapping("detail")
    public HttpResult<CertificateDeploy> detail(@RequestBody CertificateDeployDetailReq req) {
        return ResponseWrap.success(certificateDeployService.detail(req));
    }

    @PostMapping("add")
    public HttpResult<Void> add(@RequestBody CertificateDeployAddReq req) {
        certificateDeployService.add(req);
        return ResponseWrap.success();
    }

    @PostMapping("deploy")
    public HttpResult<Void> deploy(@RequestBody CertificateDeployDeployReq req) {
        certificateDeployService.deploy(req);
        return ResponseWrap.success();
    }

}
