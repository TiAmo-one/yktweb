package com.team.yplus.security;

import com.mysql.cj.util.StringUtils;
import com.team.yplus.common.JSONAuthentication;
import com.team.yplus.response.RestResult;
import com.team.yplus.response.ResultCode;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("logoutOperateHandler")
public class LogoutOperateHandler extends JSONAuthentication implements LogoutHandler  {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String headerToken = httpServletRequest.getHeader("${token.header}");
        if(!StringUtils.isEmptyOrWhitespaceOnly(headerToken)) {
            SecurityContextHolder.clearContext();
        }
//        else{
//            RestResult result = RestResult.fail(ResultCode.USER_NOT_LOGIN);
//            this.WriteJSON(httpServletRequest,httpServletResponse,result);
//        }

    }
}
