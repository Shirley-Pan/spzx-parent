package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.manager.model.vo.system.SysMenuVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/3 10:30
 * description:
 */
@Service
public interface SysMenuService {

    List<SysMenu> findNodesMenu();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);


    List<SysMenuVo> findUserMenuList();
}
