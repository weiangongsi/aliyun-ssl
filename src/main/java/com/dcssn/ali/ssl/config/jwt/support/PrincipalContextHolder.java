package com.dcssn.ali.ssl.config.jwt.support;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lhy
 * @since 2023-05-06
 */
public class PrincipalContextHolder {

    public static void setPrinciple(HttpServletRequest request, Principal principal) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        requestAttributes.setAttribute("principal", principal, RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    public static Principal getPrinciple() {
        return (Principal) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute("principal", RequestAttributes.SCOPE_REQUEST);
    }

}
