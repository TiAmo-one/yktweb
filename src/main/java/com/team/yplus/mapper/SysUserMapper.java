package com.team.yplus.mapper;

import com.team.yplus.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.yplus.entity.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hakan
 * @since 2021-10-14
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> getRoleCodeByUserName(String username);
    int insertUser(SysUser sysUser);

}
