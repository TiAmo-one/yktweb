package com.team.yplus.security;

import com.team.yplus.common.JSONAuthentication;
import com.team.yplus.common.JwtTokenUtil;
import com.team.yplus.response.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {

    @Autowired
    public JwtTokenUtil jwtTokenUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        String token = jwtTokenUtil.generateToken(authentication.getName());
        httpServletResponse.setHeader(jwtTokenUtil.getHeader(),token);
        RestResult result = RestResult.succ("");

        this.WriteJSON(httpServletRequest,httpServletResponse,result);
    }
}
