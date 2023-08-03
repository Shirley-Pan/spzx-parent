package com.atguigu.spzx.mapper;


import com.atguigu.spzx.manager.model.dto.system.SysUserDto;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/7/28 19:37
 * description:
 */
@Repository
@Mapper
public interface SysUserMapper {
    SysUser selectByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysuser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteSysUser(Long userId);
}
