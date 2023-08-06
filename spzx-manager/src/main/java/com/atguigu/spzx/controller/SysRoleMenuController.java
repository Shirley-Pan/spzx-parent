package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/3 11:52
 * description:
 */
@RestController
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String,Object>> findSysRoleMenuByRoleId(@PathVariable Long roleId){
        Map<String,Object> sysRoleMenuList =sysRoleMenuService.findSysRoleMenuByRoleId(roleId);

        return Result.build(sysRoleMenuList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto){
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
