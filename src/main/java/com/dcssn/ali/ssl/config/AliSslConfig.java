package com.dcssn.ali.ssl.config;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.cas20200407.AsyncClient;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 阿里SSL Client
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Configuration
public class AliSslConfig {


    @Value("${ali.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public AsyncClient aliSslAsyncClient() {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build());

        AsyncClient client = AsyncClient.builder()
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("cas.aliyuncs.com")
                )
                .build();
        return client;
    }

}
