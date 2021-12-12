package com.team.yplus.security;

import com.team.yplus.common.JSONAuthentication;
import com.team.yplus.response.RestResult;
import com.team.yplus.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("accDeniedHandler")
public class AccDeniedHandler extends JSONAuthentication implements AccessDeniedHandler {//访问拒绝处理程序
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        RestResult restResult = RestResult.fail(ResultCode.NO_PERMISSION);
        this.WriteJSON(httpServletRequest,httpServletResponse,restResult);
    }
}
