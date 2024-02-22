package com.dcssn.ali.ssl.config.jwt.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dcssn.ali.ssl.config.jwt.properties.JwtProperties;
import com.dcssn.ali.ssl.config.jwt.support.Principal;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * jwt工具
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Component
public class JwtUtils {

    private final JwtProperties properties;

    public JwtUtils(JwtProperties properties) {
        this.properties = properties;
    }

    /**
     * 生成token
     *
     * @param principal 用户信息
     * @return token
     */
    public String getToken(Principal principal) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("principal", JSONObject.toJSONString(principal));
        // 签发时间
        builder.withIssuedAt(new Date());
        // 代表JWT的主体，即它的所有人
        builder.withSubject(principal.getUsername());
        //默认令牌过期时间7天
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, properties.getExpiresHour());
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(properties.getSecret()));
    }

    /**
     * 校验token
     *
     * @param token token
     * @return DecodedJWT
     */
    public DecodedJWT verify(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(properties.getSecret())).build();
        return build.verify(token);
    }

}
