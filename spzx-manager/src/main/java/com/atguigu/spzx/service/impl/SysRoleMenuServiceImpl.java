package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/3 11:55
 * description:
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodesMenu();
        //查询当前角色的菜单数据
       List<SysMenu> roleMenuIds= sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
       //将数据存储到map中进行返回
        Map<String,Object> result =new HashMap<>();
        result.put("sysMenuList",sysMenuList);
        result.put("roleMenuIds",roleMenuIds);

        return result;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        //获取菜单的id
        List<Map<String,Number>> menuInfo =assginMenuDto.getMenuIdList();
        if (menuInfo.size()>0&&menuInfo!=null){
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
