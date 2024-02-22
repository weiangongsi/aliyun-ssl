package com.dcssn.ali.ssl.config.jwt.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * <p>
 * 方法参数Principal自动赋值
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
public class PrincipalMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType() == Long.class && Objects.equals(parameter.getParameterName(), "username")) {
            return true;
        }
        return parameter.getParameterType() == Principal.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (Objects.equals(parameter.getParameterName(), "username")) {
            Principal principal = PrincipalContextHolder.getPrinciple();
            return principal.getUsername();
        }
        return PrincipalContextHolder.getPrinciple();
    }

}
