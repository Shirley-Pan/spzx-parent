package com.atguigu.spzx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/3 11:53
 * description:
 */
@Repository
@Mapper
public interface SysRoleUserMapper {
    void deleteByUserId(Long userId);

    void doAssgin(@Param("userId") Long userId,
                  @Param("roleId") Long roleId);

    List<Long> findSysUserRoleByUserId(Long userId);
}
