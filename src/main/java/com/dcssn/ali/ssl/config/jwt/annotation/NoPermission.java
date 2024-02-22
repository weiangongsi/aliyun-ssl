package com.dcssn.ali.ssl.config.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 不去需要权限的注解
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoPermission {
}
