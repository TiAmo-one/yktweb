package com.team.yplus.mapper;

import com.team.yplus.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hakan
 * @since 2021-10-14
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    int insertAuth(SysUserRole sysUserRole);
}
