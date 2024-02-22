package com.dcssn.ali.ssl.service;

import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.vo.certificate.CertificateAddReq;
import com.dcssn.ali.ssl.vo.certificate.CertificateCancelReq;
import com.dcssn.ali.ssl.vo.certificate.vo.CertificateVo;

import java.util.List;

/**
 * <p>
 * 证书service
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public interface CertificateService {

    List<CertificateVo> list();

    void add(CertificateAddReq req);

    void cancel(CertificateCancelReq req);

    void sync();

}
