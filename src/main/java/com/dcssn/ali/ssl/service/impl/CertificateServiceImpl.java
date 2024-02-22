package com.dcssn.ali.ssl.service.impl;

import com.dcssn.ali.ssl.common.exception.ProjectException;
import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;
import com.dcssn.ali.ssl.common.util.Assert;
import com.dcssn.ali.ssl.convert.CertificateConvertMapper;
import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.repository.CertificateDeployRepository;
import com.dcssn.ali.ssl.repository.CertificateRepository;
import com.dcssn.ali.ssl.service.AliSslService;
import com.dcssn.ali.ssl.service.CertificateService;
import com.dcssn.ali.ssl.vo.certificate.CertificateAddReq;
import com.dcssn.ali.ssl.vo.certificate.CertificateCancelReq;
import com.dcssn.ali.ssl.vo.certificate.vo.CertificateVo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 证书service impl
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Service
public class CertificateServiceImpl implements CertificateService {
    @Resource
    private AliSslService aliSslService;
    @Resource
    private CertificateRepository certificateRepository;
    @Resource
    private CertificateDeployRepository certificateDeployRepository;
    @Resource
    private CertificateConvertMapper certificateConvertMapper;

    @Override
    public List<CertificateVo> list() {
        List<CertificateVo> resp = new ArrayList<>();
        List<Certificate> certificateList = certificateRepository.findAll();
        for (Certificate certificate : certificateList) {
            Optional<CertificateDeploy> certificateDeployOptional = certificateDeployRepository.findById(certificate.getDomain());
            CertificateVo certificateVo = certificateConvertMapper.certificateToCertificateVo(certificate);
            if (certificateDeployOptional.isPresent()) {
                CertificateDeploy certificateDeploy = certificateDeployOptional.get();
                certificateVo.setCronStatus(certificateDeploy.getCronStatus());
                certificateVo.setDeployDate(certificateDeploy.getDeployDate());
                certificateVo.setDeployStatus(certificateDeploy.getDeployStatus());
                certificateVo.setDeployError(certificateDeploy.getDeployError());
                certificateVo.setNextDeployDate(certificateDeploy.getNextDeployDate());
            }
            resp.add(certificateVo);
        }
        return resp;
    }

    @Override
    @Transactional
    public void add(CertificateAddReq req) {
        try {
            boolean needApply = aliSslService.checkNeedApply(req.getDomain());
            Assert.isTrue(needApply, "存在可用或申请中的证书无需申请");
            aliSslService.apply(req.getDomain());
        } catch (Exception e) {
            throw new ProjectException(ProjectResultMessage.FAIL, e.getMessage());
        } finally {
            this.sync();
        }
    }

    @Override
    @Transactional
    public void cancel(CertificateCancelReq req) {
        Optional<Certificate> optional = certificateRepository.findById(req.getInstanceId());
        if (optional.isPresent()) {
            Certificate certificate = optional.get();
            try {
                aliSslService.cancelCertificateForPackageRequest(certificate.getOrderId());
            } catch (Exception e) {
                throw new ProjectException("请求阿里云取消包请求的证书失败");
            } finally {
                this.sync();
            }
        }
    }

    @Override
    public void sync() {
        try {
            aliSslService.syncAllCertificate();
        } catch (Exception e) {
            throw new ProjectException("同步失败");
        }
    }

}
