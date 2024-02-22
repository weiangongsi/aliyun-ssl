package com.dcssn.ali.ssl.controller;

import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;
import com.dcssn.ali.ssl.common.result.HttpResult;
import com.dcssn.ali.ssl.common.result.ResponseWrap;
import com.dcssn.ali.ssl.common.util.Assert;
import com.dcssn.ali.ssl.config.jwt.support.Principal;
import com.dcssn.ali.ssl.config.jwt.util.JwtUtils;
import com.dcssn.ali.ssl.vo.login.LoginReq;
import com.dcssn.ali.ssl.vo.login.LoginResp;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 证书
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private JwtUtils jwtUtils;

    @Value("${login.username}")
    private String username;

    @Value("${login.password}")
    private String password;

    @PostMapping("")
    public HttpResult<LoginResp> list(@RequestBody LoginReq req) {
        LoginResp resp = new LoginResp();
        Assert.isTrue(username.equals(req.getUsername()) && password.equals(req.getPassword()), ProjectResultMessage.CODE_002);
        Principal principal = new Principal();
        principal.setUsername(username);
        String token = jwtUtils.getToken(principal);
        resp.setToken(token);
        return ResponseWrap.success(resp);
    }


}
