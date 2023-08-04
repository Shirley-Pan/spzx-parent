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
    List<SysMenu> selectAll();
}
