package com.team.yplus.service.impl;

import com.team.yplus.common.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


@Service
public class JwtAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String login(String username ,String password){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (Exception e){
            throw new RuntimeException("用户名密码错误");
        }
        User loginUser = (User) authentication.getPrincipal();

        return jwtTokenUtil.generateToken(loginUser);
    }
}
