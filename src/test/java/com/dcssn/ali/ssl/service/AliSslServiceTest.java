package com.dcssn.ali.ssl.service;

import com.aliyun.sdk.service.cas20200407.models.GetUserCertificateDetailResponseBody;
import com.dcssn.ali.ssl.AliSslApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

class AliSslServiceTest extends AliSslApplicationTests {

    @Resource
    private AliSslService aliSslService;

    @Test
    void syncCertificate() throws Exception {
        aliSslService.syncAllCertificate();
    }

    @Test
    void getUserCertificateDetail() throws Exception {
        GetUserCertificateDetailResponseBody userCertificateDetail = aliSslService.getUserCertificateDetail(12469070L);
        // pem
        String cert = userCertificateDetail.getCert();
        // key
        String key = userCertificateDetail.getKey();

        System.out.println();
    }

}