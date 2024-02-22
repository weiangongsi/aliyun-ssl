package com.dcssn.ali.ssl.controller;

import com.dcssn.ali.ssl.common.result.HttpResult;
import com.dcssn.ali.ssl.common.result.ResponseWrap;
import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.service.CertificateService;
import com.dcssn.ali.ssl.vo.certificate.CertificateAddReq;
import com.dcssn.ali.ssl.vo.certificate.CertificateCancelReq;
import com.dcssn.ali.ssl.vo.certificate.vo.CertificateVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 证书
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@RestController
@RequestMapping("certificate")
public class CertificateController {

    @Resource
    private CertificateService certificateService;

    @PostMapping("list")
    public HttpResult<List<CertificateVo>> list() {
        return ResponseWrap.success(certificateService.list());
    }

    @PostMapping("add")
    public HttpResult<Void> add(@RequestBody CertificateAddReq req) {
        certificateService.add(req);
        return ResponseWrap.success();
    }

    @PostMapping("cancel")
    public HttpResult<Void> cancel(@RequestBody CertificateCancelReq req) {
        certificateService.cancel(req);
        return ResponseWrap.success();
    }

    @PostMapping("sync")
    public HttpResult<Void> sync( ) {
        certificateService.sync();
        return ResponseWrap.success();
    }

}
