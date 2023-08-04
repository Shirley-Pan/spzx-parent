package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/3 10:29
 * description:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenuList=sysMenuMapper.selectAll();
        if (CollectionUtils.isEmpty(sysMenuList))return null;
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);//构建树形数据
        return treeList;
    }

    //查询所有菜单
    //把查询出来的所有菜单list集合，转换要求的数据格式
    //使用递归实现，第一个原则，入口 parentid=0
    //
}
