package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.system.AssginMenuDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/3 11:57
 * description:
 */
@Service
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
