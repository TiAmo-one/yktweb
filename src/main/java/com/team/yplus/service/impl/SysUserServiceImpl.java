package com.team.yplus.service.impl;

import com.team.yplus.entity.SysUser;
import com.team.yplus.entity.SysUserRole;
import com.team.yplus.mapper.SysUserMapper;
import com.team.yplus.mapper.SysUserRoleMapper;
import com.team.yplus.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hakan
 * @since 2021-10-14
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Transactional
    public void insertUser(String username,String password){

        SysUser sysUser = new SysUser(username,password);
        sysUserMapper.insertUser(sysUser);
        SysUserRole sysUserRole = new SysUserRole(sysUser.getId(),1);
        System.out.println(sysUserRole.toString());
        sysUserRoleMapper.insertAuth(sysUserRole);
    }
}
