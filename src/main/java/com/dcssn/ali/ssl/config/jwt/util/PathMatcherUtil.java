package com.dcssn.ali.ssl.config.jwt.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * <p>
 * 路径是否匹配
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
public class PathMatcherUtil {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 校验当前路径是否和参数匹配
     *
     * @param pathPatterns 路径列表
     * @return true 包含在路径列表里
     */
    public static boolean match(List<String> pathPatterns) {
        if (pathPatterns == null) {
            return false;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String uri = request.getServletPath();
        return pathPatterns.stream().anyMatch(excludePathPattern -> antPathMatcher.match(excludePathPattern, uri));
    }

}
