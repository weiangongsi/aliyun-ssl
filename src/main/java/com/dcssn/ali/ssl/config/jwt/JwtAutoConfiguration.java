package com.dcssn.ali.ssl.config.jwt;

import cn.hutool.core.util.StrUtil;
import com.dcssn.ali.ssl.config.jwt.interceptor.JwtInterceptor;
import com.dcssn.ali.ssl.config.jwt.properties.JwtProperties;
import com.dcssn.ali.ssl.config.jwt.support.PrincipalMethodArgumentResolver;
import com.dcssn.ali.ssl.config.jwt.util.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * jwt权限认证
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@RestController
public class JwtAutoConfiguration implements WebMvcConfigurer {

    /**
     * 401 路径
     */
    public static final String UNAUTHORIZED_PATH = "/401";

    @Resource
    private JwtProperties properties;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (StrUtil.isBlank(properties.getExcludePathPatterns())) {
            properties.setExcludePathPatterns(JwtAutoConfiguration.UNAUTHORIZED_PATH);
        } else {
            properties.setExcludePathPatterns(properties.getExcludePathPatterns() + "," + UNAUTHORIZED_PATH);
        }
        registry.addInterceptor(new JwtInterceptor(properties, jwtUtils))
                // 拦截的路径
                .addPathPatterns("/**")
                // 排除的路由
                .excludePathPatterns(properties.getExcludePathPatterns().split(","));

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PrincipalMethodArgumentResolver());
    }

    @RequestMapping(JwtAutoConfiguration.UNAUTHORIZED_PATH)
    public HashMap<String, Object> error401() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", 401);
        data.put("msg", "权限错误");
        return data;
    }

}
