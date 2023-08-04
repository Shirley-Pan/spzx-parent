package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.mapper.SysRoleUserMapper;
import com.atguigu.spzx.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/3 11:55
 * description:
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Override
    public void doAssgin(AssginRoleDto assginRoleDto) {
        //删除之前的所有的用户对应的角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        //分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        //遇到集合要么return，要么遍历。在遍历中将角色放到集合中
        roleIdList.forEach(roleId ->{
            sysRoleUserMapper.doAssgin(assginRoleDto.getUserId(),roleId);
        });

    }
}
