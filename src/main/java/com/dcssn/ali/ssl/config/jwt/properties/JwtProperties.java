package com.dcssn.ali.ssl.config.jwt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * jwt配置
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 加密盐
     */
    private String secret="Y3BJ^dPsSvyZL*2V";

    /**
     * token名称
     */
    private String tokenName = "token";

    /**
     * 过期时间，单位：小时
     */
    private int expiresHour=24;

    /**
     * 不校验权限的路径，逗号分隔
     */
    private String excludePathPatterns;
}
