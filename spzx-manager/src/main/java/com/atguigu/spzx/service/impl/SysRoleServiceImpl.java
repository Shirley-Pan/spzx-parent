package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.dto.system.SysRoleDto;
import com.atguigu.spzx.manager.model.entity.system.SysRole;
import com.atguigu.spzx.mapper.SysRoleMapper;
import com.atguigu.spzx.mapper.SysRoleUserMapper;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/1 10:50
 * description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Integer roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //查询所有的角色数据
        List<SysRole> sysRoleList =sysRoleMapper.findAllRoles();
        //查询当前登录用户的角色数据
        List<Long> sysRoles= sysRoleUserMapper.findSysUserRoleByUserId(userId);

        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("allRolesList",sysRoleList);
        resultMap.put("sysUserRoles",sysRoles);
        return resultMap;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        //向pageHelper 设置分页参数，当前页和每页记录数
        PageHelper.startPage(current,limit);
        //查询表所有记录
        List<SysRole> SysRolelist =sysRoleMapper.findByPage(sysRoleDto);
        //通过pageInfo把查询出来所有记录分页数据返回
        PageInfo<SysRole> pageInfo =new PageInfo<>(SysRolelist);
        return pageInfo;
    }


}
