package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/3 10:35
 * description:
 */
@Repository
@Mapper
public interface SysMenuMapper {

    List<SysMenu> selectAllMenu();

    void insert(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    int countByParentId(Long id);

    List<SysMenu> selectMenuListByUserId(Long userId);
}
