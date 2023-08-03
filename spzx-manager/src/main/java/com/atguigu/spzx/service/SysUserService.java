package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.system.LoginDto;
import com.atguigu.spzx.manager.model.dto.system.SysUserDto;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/7/28 19:37
 * description:
 */
@Service
public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer currentPage, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteSysUser(Long userId);
}
