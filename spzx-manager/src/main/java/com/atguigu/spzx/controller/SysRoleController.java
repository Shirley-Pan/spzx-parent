package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.dto.system.SysRoleDto;
import com.atguigu.spzx.manager.model.entity.system.SysRole;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/1 10:44
 * description:
 */
@Tag(name="角色接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //删除
    @DeleteMapping("deleteById/{roleId}")
    public Result deleteById(@PathVariable  Long roleId){
        sysRoleService.deleteById(roleId);
        return  Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //3.修改角色
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return  Result.build(null,ResultCodeEnum.SUCCESS);

    }

    //2.角色添加
    @PostMapping("saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //分页条件查询,通过模糊查询搜索符合条件的对象
    //分页：当前页current，和每页显示记录数，limit
    //条件对象
    @PostMapping("findByPage/{current}/{limit}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto,
                                                @PathVariable Integer current,
                                                @PathVariable Integer limit){
        PageInfo<SysRole> pageInfo =sysRoleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("findAllRoles/{userId}")
    public Result<Map<String, Object>> findAllRoles(@PathVariable Long userId){
        Map<String,Object> resultMap =sysRoleService.findAllRoles(userId);
        return Result.build(resultMap,ResultCodeEnum.SUCCESS);
    }



}
