package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.dto.system.SysRoleDto;
import com.atguigu.spzx.manager.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/1 19:41
 * description:
 */
@Mapper
@Repository
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Integer roleId);

    List<SysRole> findAllRoles();
}
