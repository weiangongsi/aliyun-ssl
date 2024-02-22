package com.dcssn.ali.ssl.service;

import com.aliyun.sdk.service.alidns20150109.AsyncClient;
import com.aliyun.sdk.service.alidns20150109.models.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * DNS Service
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Service
public class AliDnsService {

    @Resource
    private AsyncClient aliDnsAsyncClient;


    /**
     * 添加解析记录
     */
    public void addDomainRecord(String domainName, String rr, String type, String value) throws Exception {
        DescribeDomainRecordsRequest describeDomainRecordsRequest = DescribeDomainRecordsRequest.builder().domainName(domainName).RRKeyWord(rr).searchMode("ADVANCED").type(type).build();
        CompletableFuture<DescribeDomainRecordsResponse> describeDomainRecordsResponse = aliDnsAsyncClient.describeDomainRecords(describeDomainRecordsRequest);
        DescribeDomainRecordsResponseBody describeDomainRecordsResponseBody = describeDomainRecordsResponse.get().getBody();
        Long totalCount = describeDomainRecordsResponseBody.getTotalCount();
        if (totalCount == 0) {
            AddDomainRecordRequest addDomainRecordRequest = AddDomainRecordRequest.builder().domainName(domainName).rr(rr).type(type).value(value).build();
            CompletableFuture<AddDomainRecordResponse> addDomainRecordResponse = aliDnsAsyncClient.addDomainRecord(addDomainRecordRequest);
            addDomainRecordResponse.get().getBody();
        } else {
            List<DescribeDomainRecordsResponseBody.Record> recordList = describeDomainRecordsResponseBody.getDomainRecords().getRecord();
            DescribeDomainRecordsResponseBody.Record record = recordList.get(0);
            String curValue = record.getValue();
            if (!curValue.equals(value)) {
                UpdateDomainRecordRequest updateDomainRecordRequest = UpdateDomainRecordRequest.builder().recordId(record.getRecordId()).rr(rr).type(type).value(value).build();
                aliDnsAsyncClient.updateDomainRecord(updateDomainRecordRequest);
            }
        }

    }

}
