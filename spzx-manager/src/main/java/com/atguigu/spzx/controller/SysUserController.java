package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.dto.system.SysUserDto;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/2 9:15
 * description:
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    //分页查询
    @GetMapping("/findByPage/{currentPage}/{pageSize}")
    public Result findByPage(SysUserDto sysUserDto,
                             @PathVariable Integer currentPage,
                             @PathVariable Integer pageSize){
        PageInfo<SysUser> pageInfo =sysUserService.findByPage(sysUserDto,currentPage,pageSize);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //  用户添加
    @PostMapping("saveSysUser")
    public Result saveSysUser (@RequestBody SysUser sysUser){
        sysUserService.saveSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    //用户修改
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteSysUser/{userId}")
    public Result deleteSysUser(@PathVariable Long userId){
        sysUserService.deleteSysUser(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}
