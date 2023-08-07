package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.system.SysRoleDto;
import com.atguigu.spzx.manager.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/1 10:49
 * description:
 */
@Service
public interface SysRoleService {

    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);


}
