package com.dcssn.ali.ssl;

import com.dcssn.ali.ssl.entity.CertificateDeploy;
import com.dcssn.ali.ssl.repository.CertificateDeployRepository;
import com.dcssn.ali.ssl.service.CertificateDeployService;
import com.dcssn.ali.ssl.vo.certificate.deploy.CertificateDeployDeployReq;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootTest
public class AliSslApplicationTests {

    @Resource
    private CertificateDeployService certificateDeployService;
    @Resource
    private CertificateDeployRepository certificateDeployRepository;

    @Test
    public void testScheduleTask1() {
        //开启自动部署并且下次部署在今天之前
        List<CertificateDeploy> list = certificateDeployRepository.findByCronStatusAndNextDeployDateBeforeOrDeployDateIsNull("ON", LocalDate.now().plusDays(1));
        System.out.println();
    }

    public static void main(String[] args) {
        String d = "_dnsauth.101.dcssn.com";
        String[] split = d.split("\\.");
        String firstDomain = split[split.length - 2] + "." + split[split.length - 1];
        String rr = d.substring(0, d.length() - firstDomain.length() - 1);
        System.out.println(rr);
        System.out.println(firstDomain);
    }

}
