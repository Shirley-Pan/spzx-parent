package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysRoleUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/3 9:02
 * description:
 */
@RestController
@RequestMapping(value = "/admin/system/sysRoleUser")
public class SysRoleUserController {
    @Autowired
    private SysRoleUserService sysRoleUserService;
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        sysRoleUserService.doAssgin(assginRoleDto);
        return  Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
