package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/3 9:11
 * description:
 */
@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @GetMapping("findNodes")
    public Result<List<SysMenu>> findNodes(){
       List<SysMenu> list= sysMenuService.findNodes();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }


}
