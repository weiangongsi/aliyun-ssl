package com.dcssn.ali.ssl.service;

import com.aliyun.sdk.service.cas20200407.AsyncClient;
import com.aliyun.sdk.service.cas20200407.models.*;
import com.dcssn.ali.ssl.common.exception.ProjectException;
import com.dcssn.ali.ssl.entity.Certificate;
import com.dcssn.ali.ssl.repository.CertificateRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * SSL Service
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Service
public class AliSslService {


    @Resource
    private AsyncClient aliSslAsyncClient;

    @Resource
    private AliDnsService aliDnsService;

    @Resource
    private CertificateRepository certificateRepository;

    @Transactional
    public void syncAllCertificate() {
        try {
            this.syncCertificate(null);
            this.syncCertificate("CERT");
        } catch (Exception e) {
            throw new ProjectException("同步失败");
        }
    }

    /**
     * 同步证书
     */
    private void syncCertificate(String orderType) throws Exception {
        // 查询用户证书或者订单列表
        ListUserCertificateOrderRequest listUserCertificateOrderRequest = ListUserCertificateOrderRequest.builder()
                .orderType(orderType)
                .build();
        CompletableFuture<ListUserCertificateOrderResponse> response = aliSslAsyncClient.listUserCertificateOrder(listUserCertificateOrderRequest);
        ListUserCertificateOrderResponseBody body = response.get().getBody();
        List<ListUserCertificateOrderResponseBody.CertificateOrderList> certificateOrderList = body.getCertificateOrderList();
        List<Long> orderIdList = new ArrayList<>();
        for (ListUserCertificateOrderResponseBody.CertificateOrderList order : certificateOrderList) {
            Optional<Certificate> optional = certificateRepository.findById(order.getInstanceId());
            Certificate certificate = new Certificate();
            if (optional.isPresent()) {
                certificate = optional.get();
            } else {
                certificate.setInstanceId(order.getInstanceId());
            }
            if (StringUtils.hasText(order.getCertType())) {
                certificate.setCertType(order.getCertType());
            }
            if (StringUtils.hasText(order.getName())) {
                certificate.setName(order.getName());
            }
            if (StringUtils.hasText(order.getDomain())) {
                certificate.setDomain(order.getDomain());
            }
            if (StringUtils.hasText(order.getSans())) {
                certificate.setSans(order.getSans());
            }
            if (order.getOrderId() != null) {
                certificate.setOrderId(order.getOrderId());
                orderIdList.add(order.getOrderId());
            }
            if (order.getCertificateId() != null) {
                certificate.setCertificateId(order.getCertificateId());
            }
            if (StringUtils.hasText(order.getStatus())) {
                certificate.setStatus(order.getStatus());
            }
            if (StringUtils.hasText(order.getStartDate())) {
                certificate.setStartDate(LocalDate.parse(order.getStartDate()));
            }
            if (StringUtils.hasText(order.getEndDate())) {
                certificate.setEndDate(LocalDate.parse(order.getEndDate()));
            }
            certificateRepository.save(certificate);
        }
        certificateRepository.deleteByOrderIdNotIn(orderIdList);
    }

    /**
     * 获取证书详情
     *
     * @param certId 证书 ID
     */
    public GetUserCertificateDetailResponseBody getUserCertificateDetail(Long certId) throws Exception {
        GetUserCertificateDetailRequest getUserCertificateDetailRequest = GetUserCertificateDetailRequest.builder().certId(certId).build();
        CompletableFuture<GetUserCertificateDetailResponse> response = aliSslAsyncClient.getUserCertificateDetail(getUserCertificateDetailRequest);
        return response.get().getBody();
    }

    /**
     * 取消包请求的证书
     *
     * @param orderId 订单 ID
     */
    public void cancelCertificateForPackageRequest(Long orderId) throws Exception {
        CancelCertificateForPackageRequestRequest cancelCertificateForPackageRequestRequest = CancelCertificateForPackageRequestRequest.builder().orderId(orderId).build();
        CompletableFuture<CancelCertificateForPackageRequestResponse> response = aliSslAsyncClient.cancelCertificateForPackageRequest(cancelCertificateForPackageRequestRequest);
        response.get().getBody();
    }

    public boolean checkNeedApply(String domain) throws Exception {
        // 查询用户证书或者订单列表
        ListUserCertificateOrderRequest listUserCertificateOrderRequest = ListUserCertificateOrderRequest.builder().keyword(domain).build();
        CompletableFuture<ListUserCertificateOrderResponse> response = aliSslAsyncClient.listUserCertificateOrder(listUserCertificateOrderRequest);
        ListUserCertificateOrderResponseBody body = response.get().getBody();
        List<ListUserCertificateOrderResponseBody.CertificateOrderList> certificateOrderList = body.getCertificateOrderList();
        // 查询到已存在的证书
        if (body.getTotalCount() > 0) {
            for (ListUserCertificateOrderResponseBody.CertificateOrderList order : certificateOrderList) {
                Long certificateId = order.getCertificateId();
                String status = order.getStatus();
                // 待申请 审核中 已签发
                if ("PAYED".equals(status) || "CHECKING".equals(status) || "ISSUED".equals(status)) {
                    return false;
                }
                // 审核失败 即将过期 已过期
                else if ("CHECKED_FAIL".equals(status) || "WILLEXPIRED".equals(status) || "EXPIRED".equals(status)) {
                    // 删除旧的证书
                    this.deleteUserCertificate(certificateId);
                }
            }
        }
        return true;
    }

    public void apply(String domain) throws Exception {
        CreateCertificateForPackageRequestRequest createCertificateForPackageRequestRequest = CreateCertificateForPackageRequestRequest.builder().productCode("digicert-free-1-free").domain(domain).validateType("DNS").build();
        CompletableFuture<CreateCertificateForPackageRequestResponse> response2 = aliSslAsyncClient.createCertificateForPackageRequest(createCertificateForPackageRequestRequest);
        response2.get().getBody();
    }

    public void checkCertificateVerifyState(Long orderId) throws Exception {
        DescribeCertificateStateResponseBody describeCertificateStateResponseBody = this.describeCertificateState(orderId);
        String type = describeCertificateStateResponseBody.getType();
        // 待验证
        if ("domain_verify".equals(type)) {
            String recordDomain = describeCertificateStateResponseBody.getRecordDomain();
            String recordValue = describeCertificateStateResponseBody.getRecordValue();
            String[] split = recordDomain.split("\\.");
            String domain = split[split.length - 2] + "." + split[split.length - 1];
            String rr = recordDomain.substring(0, recordDomain.length() - domain.length() - 1);
            aliDnsService.addDomainRecord(domain, rr, "txt", recordValue);
        }
    }


    /**
     * 查询DV证书的申请状态
     *
     * @param orderId 要查询的证书申请订单的 ID
     */
    private DescribeCertificateStateResponseBody describeCertificateState(long orderId) throws Exception {
        DescribeCertificateStateRequest describeCertificateStateRequest = DescribeCertificateStateRequest.builder().orderId(orderId).build();
        CompletableFuture<DescribeCertificateStateResponse> response = aliSslAsyncClient.describeCertificateState(describeCertificateStateRequest);
        return response.get().getBody();
    }

    /**
     * 删除证书
     *
     * @param certId 证书 ID
     */
    public void deleteUserCertificate(Long certId) throws Exception {
        DeleteUserCertificateRequest deleteUserCertificateRequest = DeleteUserCertificateRequest.builder().certId(certId).build();
        CompletableFuture<DeleteUserCertificateResponse> response = aliSslAsyncClient.deleteUserCertificate(deleteUserCertificateRequest);
        response.get().getBody();
    }


}
