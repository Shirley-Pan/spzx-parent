package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/3 11:52
 * description:
 */
@RestController
@RequestMapping("")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    public Result findSysRoleMenuByRoleId(){
        return null;
    }
}
