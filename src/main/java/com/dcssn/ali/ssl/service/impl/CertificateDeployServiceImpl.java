package com.dcssn.ali.ssl.service.impl;

import com.aliyun.sdk.service.cas20200407.models.GetUserCertificateDetailResponseBody;
import com.dcssn.ali.ssl.common.exception.ProjectException;
import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;
import com.dcssn.ali.ssl.common.util.Assert;
import com.dcssn.ali.ssl.convert.CertificateDeployConvertMapper;
import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.repository.CertificateDeployRepository;
import com.dcssn.ali.ssl.repository.CertificateRepository;
import com.dcssn.ali.ssl.service.AliSslService;
import com.dcssn.ali.ssl.service.CertificateDeployService;
import com.dcssn.ali.ssl.service.SshService;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployAddReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDeployReq;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDetailReq;
import com.jcraft.jsch.Session;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <p>
 * 证书部署service impl
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Service
public class CertificateDeployServiceImpl implements CertificateDeployService {

    @Resource
    private SshService sshService;

    @Resource
    private AliSslService aliSslService;

    @Resource
    private CertificateRepository certificateRepository;

    @Resource
    private CertificateDeployRepository certificateDeployRepository;

    @Resource
    private CertificateDeployConvertMapper certificateDeployConvertMapper;

    @Override
    public CertificateDeploy detail(CertificateDeployDetailReq req) {
        Optional<CertificateDeploy> optional = certificateDeployRepository.findById(req.getDomain());
        return optional.orElse(null);
    }

    @Override
    public void add(CertificateDeployAddReq req) {
        // 连接测试
        try {
            Session session = sshService.getSession(req.getHost(), req.getPort(), req.getUsername(), req.getPassword());
            session.disconnect();
        } catch (Exception e) {
            throw new ProjectException(ProjectResultMessage.CODE_001);
        }
        Optional<Certificate> certificateOptional = certificateRepository.findFirstByDomainAndStatus(req.getDomain(), "ISSUED");
        if (certificateOptional.isEmpty()) {
            certificateOptional = certificateRepository.findFirstByDomainAndStatus(req.getDomain(), "WILLEXPIRED");
        }
        Optional<CertificateDeploy> certificateDeployOptional = certificateDeployRepository.findById(req.getDomain());
        CertificateDeploy certificateDeploy;
        if (certificateDeployOptional.isPresent()) {
            certificateDeploy = certificateDeployOptional.get();
            certificateDeploy.setCronStatus(req.getCronStatus());
            certificateDeploy.setHost(req.getHost());
            certificateDeploy.setPort(req.getPort());
            certificateDeploy.setUsername(req.getUsername());
            certificateDeploy.setPassword(req.getPassword());
            certificateDeploy.setPath(req.getPath());
            certificateDeploy.setShell(req.getShell());
        } else {
            certificateDeploy = certificateDeployConvertMapper.certificateDeployAddReqToCertificateDeploy(req);
        }
        if (certificateOptional.isPresent()) {
            Certificate certificate = certificateOptional.get();
            certificateDeploy.setNextDeployDate(certificate.getEndDate().minusDays(1));
        }
        certificateDeployRepository.save(certificateDeploy);
    }

    @Override
    public void deploy(CertificateDeployDeployReq req) {
        Optional<CertificateDeploy> certificateDeployOptional = certificateDeployRepository.findById(req.getDomain());
        Assert.isTrue(certificateDeployOptional.isPresent(), "部署配置不存在");
        CertificateDeploy certificateDeploy = certificateDeployOptional.get();
        Optional<Certificate> certificateOptional = certificateRepository.findFirstByDomainAndStatus(req.getDomain(), "ISSUED");
        Assert.isTrue(certificateOptional.isPresent(), "不存在已签发的证书");
        Certificate certificate = certificateOptional.get();
        // 连接测试
        try {
            Session session = sshService.getSession(certificateDeploy.getHost(), certificateDeploy.getPort(), certificateDeploy.getUsername(), certificateDeploy.getPassword());
            GetUserCertificateDetailResponseBody userCertificateDetail = aliSslService.getUserCertificateDetail(certificate.getCertificateId());
            // pem
            String cert = userCertificateDetail.getCert();
            // key
            String key = userCertificateDetail.getKey();
            // domain
            String domain = certificate.getDomain();
            String path = certificateDeploy.getPath();
            sshService.uploadFile(session, path, domain + ".pem", cert);
            sshService.uploadFile(session, path, domain + ".key", key);
            // shell
            String shell = certificateDeploy.getShell();
            if (StringUtils.hasText(shell)) {
                sshService.execShell(session, shell);
            }
            session.disconnect();
            certificateDeploy.setNextDeployDate(certificate.getEndDate().minusDays(1));
            certificateDeploy.setDeployDate(LocalDate.now());
            certificateDeploy.setDeployStatus("SUCCESS");
        } catch (Exception e) {
            certificateDeploy.setDeployStatus("FAIL");
            certificateDeploy.setDeployError(e.getMessage());
            throw new ProjectException("连接服务器失败，请检查服务器连接信息");
        }
        certificateDeployRepository.save(certificateDeploy);
    }

}
