package com.team.yplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.team.yplus.entity.SysUser;
import com.team.yplus.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();//条件构造器
        wrapper.eq(SysUser::getUsername,username);
        SysUser sysUser =  sysUserMapper.selectOne(wrapper);

        if(sysUser==null){
            throw new UsernameNotFoundException(String.format("%s用户名不存在",username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> codeList = sysUserMapper.getRoleCodeByUserName(username);
        codeList.forEach(code->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
            authorities.add(simpleGrantedAuthority);
        });

        return new User(username,sysUser.getPassword(),authorities);
    }
}
