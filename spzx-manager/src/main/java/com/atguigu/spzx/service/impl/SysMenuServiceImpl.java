package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/3 10:29
 * description:
 */
@Service
public class SysMenuServiceImpl {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    //查询所有菜单
    //把查询出来的所有菜单list集合，转换要求的数据格式
    //使用递归实现，第一个原则，入口 parentid=0
    //
}
