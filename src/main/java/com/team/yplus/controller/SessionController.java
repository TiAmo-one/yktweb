package com.team.yplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.team.yplus.entity.SysUser;
import com.team.yplus.mapper.SysUserMapper;
import com.team.yplus.response.RestResult;
import com.team.yplus.response.ResultCode;
import com.team.yplus.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SessionController {

    @Autowired
    SysUserMapper sysUserMapper;


    @Autowired
    SysUserServiceImpl sysUserService;

    @PostMapping("/register/")
    public RestResult registration(@RequestParam("user_id")String name,@RequestParam("password")String password){
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();//条件构造器
        wrapper.eq(SysUser::getUsername,name);
        SysUser sysUser =  sysUserMapper.selectOne(wrapper);

        if(sysUser!=null){
            return RestResult.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage(),"");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        try{
            sysUserService.insertUser(name,encode);
        } catch (Exception e) {
           return RestResult.fail(ResultCode.COMMON_FAIL.getCode(),ResultCode.COMMON_FAIL.getMessage(),"");
        }
        return RestResult.succ(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),"");
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public String users(){
        return "users";
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_user','ROLE_admin')")
    public String roles(){
        return "roles";
    }


    @GetMapping("/menus")
    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    public String menus(){
        return "menus";
    }


    @GetMapping("/others")
    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    public String others(){
        return "others";
    }

    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
