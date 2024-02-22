package com.dcssn.ali.ssl.config.jwt.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dcssn.ali.ssl.config.jwt.JwtAutoConfiguration;
import com.dcssn.ali.ssl.config.jwt.annotation.NoPermission;
import com.dcssn.ali.ssl.config.jwt.annotation.Permission;
import com.dcssn.ali.ssl.config.jwt.properties.JwtProperties;
import com.dcssn.ali.ssl.config.jwt.support.Principal;
import com.dcssn.ali.ssl.config.jwt.support.PrincipalContextHolder;
import com.dcssn.ali.ssl.config.jwt.util.JwtUtils;
import com.dcssn.ali.ssl.config.jwt.util.PathMatcherUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * jwt权限认证拦截实现类
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtProperties properties;
    private final JwtUtils jwtUtils;

    private final List<String> excludePathPatterns;

    public JwtInterceptor(JwtProperties properties, JwtUtils jwtUtils) {
        this.properties = properties;
        this.jwtUtils = jwtUtils;
        String excludePathPatternsStr = properties.getExcludePathPatterns();
        if (StringUtils.hasText(excludePathPatternsStr)) {
            excludePathPatterns = new ArrayList<>(Arrays.asList(excludePathPatternsStr.split(",")));
        } else {
            excludePathPatterns = new ArrayList<>();
        }
        excludePathPatterns.add("/shifu/401");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        if (PathMatcherUtil.match(excludePathPatterns)) {
            return true;
        }
        if (handler instanceof HandlerMethod handlerMethod) {
            NoPermission noPermissionOnClass = handlerMethod.getMethod().getDeclaringClass().getAnnotation(NoPermission.class);
            if (noPermissionOnClass != null) {
                return true;
            }
            NoPermission noPermissionOnMethod = handlerMethod.getMethodAnnotation(NoPermission.class);
            if (noPermissionOnMethod != null) {
                return true;
            }
        }
        String token = request.getHeader(properties.getTokenName());
        List<String> permissionCodeList = new ArrayList<>();
        try {
            // 没有token返回没有权限
            if (StrUtil.isBlank(token)) {
                send401(request, response);
                return false;
            } else {
                DecodedJWT jwt = jwtUtils.verify(token);
                String userJson = jwt.getClaim("principal").asString();
                Principal principal = JSONObject.parseObject(userJson, Principal.class);
                if (principal.getPermissionCodeList() != null) {
                    permissionCodeList = principal.getPermissionCodeList();
                }
                PrincipalContextHolder.setPrinciple(request, principal);
            }
            if (handler instanceof HandlerMethod handlerMethod) {
                // 校验token权限
                Permission permissionAnnotationOnClass = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Permission.class);
                // 没有角色权限
                if (permissionAnnotationOnClass != null && Arrays.stream(permissionAnnotationOnClass.value()).noneMatch(permissionCodeList::contains)) {
                    send401(request, response);
                    return false;
                }
                Permission permissionAnnotationOnMethod = handlerMethod.getMethodAnnotation(Permission.class);
                // 没有角色权限
                if (permissionAnnotationOnMethod != null && Arrays.stream(permissionAnnotationOnMethod.value()).noneMatch(permissionCodeList::contains)) {
                    send401(request, response);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error("token无效！ 错误 ->", e);
            send401(request, response);
            return false;
        }
    }

    private void send401(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 直接response write会导致结果直接返回前端不会经过ResponseBodyAdvice处理
        // 直接抛出异常不会被ExceptionHandler处理
        // 解决方案是写个401然后转发到这个接口
        request.getRequestDispatcher(JwtAutoConfiguration.UNAUTHORIZED_PATH).forward(request, response);
    }

}
