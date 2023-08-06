package com.atguigu.spzx.controller;

import cn.hutool.core.date.DateUnit;
import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.system.SysMenuVo;
import com.atguigu.spzx.manager.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.manager.model.dto.system.LoginDto;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.manager.model.vo.system.LoginVo;
import com.atguigu.spzx.service.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/7/28 19:36
 * description:
 */
@RestController
@Tag(name = "用户接口")
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    //退出，主要是清除redis中的token，因为前端框架自带了退出功能
    @GetMapping("logout")
    public Result logout(@RequestHeader String token){
         sysUserService.logout(token);
        return  Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //获取用户信息
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        SysUser sysUser=sysUserService.getUserInfo(token);
        return Result.build(sysUser,ResultCodeEnum.SUCCESS);

    }

    //生成图片验证码
    @Autowired
    private ValidateService validateService;
    @Operation(summary = "图片验证码")
    @GetMapping("/generateValidateCode")
    public Result generateValidateCode(){
        ValidateCodeVo validateCodeVo =validateService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }


    //登录功能
    //loginDto封装了提交过来的数据，loginvo封装返回数据
    @Operation(summary = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo= sysUserService.login(loginDto);//返回的是一个对象或者是空值
        return  Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("menus")
    public Result menus(){
        List<SysMenuVo> sysMenuVoList=sysMenuService.findUserMenuList();
        return Result.build(sysMenuVoList,ResultCodeEnum.SUCCESS);
    }

}

