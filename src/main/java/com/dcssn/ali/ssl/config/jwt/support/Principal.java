package com.dcssn.ali.ssl.config.jwt.support;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * jwt用户信息
 * <p>
 *
 * @author lhy
 * @since 2023-03-09
 */
@Data
public class Principal implements Serializable {

    /**
     * 用户id
     */
    private String username;

    /**
     * 权限列表
     */
    private List<String> permissionCodeList;

}
