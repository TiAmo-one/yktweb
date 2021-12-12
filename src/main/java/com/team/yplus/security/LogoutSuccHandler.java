package com.team.yplus.security;

import com.team.yplus.common.JSONAuthentication;
import com.team.yplus.response.RestResult;
import com.team.yplus.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("logoutSuccHandler")
public class LogoutSuccHandler extends JSONAuthentication implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        RestResult restResult = RestResult.succ(ResultCode.SUCCESS);
        this.WriteJSON(httpServletRequest,httpServletResponse,restResult);
    }
}
