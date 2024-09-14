package com.dcssn.ali.ssl.job;

import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.repository.CertificateDeployRepository;
import com.dcssn.ali.ssl.repository.CertificateRepository;
import com.dcssn.ali.ssl.service.AliSslService;
import com.dcssn.ali.ssl.service.CertificateDeployService;
import com.dcssn.ali.ssl.service.CertificateService;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDeployReq;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 **/
@Slf4j
@Component
@EnableScheduling
public class ScheduleTask {

    @Resource
    private AliSslService aliSslService;
    @Resource
    private CertificateService certificateService;
    @Resource
    private CertificateRepository certificateRepository;
    @Resource
    private CertificateDeployService certificateDeployService;
    @Resource
    private CertificateDeployRepository certificateDeployRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void testScheduleTask1() throws Exception {
        // 开启自动部署并且下次部署在今天之前
        List<CertificateDeploy> list = certificateDeployRepository.findByCronStatusAndNextDeployDateBeforeOrDeployDateIsNull("ON", LocalDate.now().plusDays(1));
        for (CertificateDeploy certificateDeploy : list) {
            log.info("开始部署" + certificateDeploy.getDomain() + "证书到" + certificateDeploy.getHost());
            Optional<Certificate> certificateOptional = certificateRepository.findFirstByDomainAndStatus(certificateDeploy.getDomain(), "ISSUED");
            if (certificateOptional.isPresent()) {
                certificateDeployService.deploy(new CertificateDeployDeployReq(certificateDeploy.getDomain()));
            } else {
                // 删除证书
                List<Certificate> certificateList = certificateRepository.findByDomain(certificateDeploy.getDomain());
                boolean existsApplying = false;
                for (Certificate certificate : certificateList) {
                    // 待申请，审核中，已签发
                    if ("PAYED".equals(certificate.getStatus()) || "CHECKING".equals(certificate.getStatus()) || "ISSUED".equals(certificate.getStatus())) {
                        existsApplying = true;
                        continue;
                    }
                    if (certificate.getCertificateId() != null) {
                        aliSslService.deleteUserCertificate(certificate.getCertificateId());
                        certificateRepository.deleteById(certificate.getInstanceId());
                    }
                }
                if (!existsApplying) {
                    log.info("部署时申请证书" + certificateDeploy.getDomain());
                    aliSslService.apply(certificateDeploy.getDomain());
                }
                certificateService.sync();
            }
        }
    }

    // dns验证
    @Scheduled(cron = "0 0/5 * * * ?")
    public void testScheduleTask3() throws Exception {
        certificateService.sync();
        List<Certificate> certificateList = certificateRepository.findByStatus("CHECKING");
        for (Certificate certificate : certificateList) {
            log.info("开始dns验证" + certificate.getDomain());
            aliSslService.checkCertificateVerifyState(certificate.getOrderId());
        }
    }

}
