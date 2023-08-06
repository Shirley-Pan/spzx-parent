package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
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
public interface SysRoleMenuMapper {
    List<SysMenu> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
