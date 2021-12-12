package com.team.yplus.security;

import com.team.yplus.common.JSONAuthentication;
import com.team.yplus.response.RestResult;
import com.team.yplus.response.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("notLoginHandler")
public class NotLoginHandler extends JSONAuthentication implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        RestResult restResult = RestResult.fail(ResultCode.USER_NOT_LOGIN);
        this.WriteJSON(httpServletRequest,httpServletResponse,restResult);
    }
}
