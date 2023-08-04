package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.system.AssginRoleDto;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/3 11:57
 * description:
 */
@Service
public interface SysRoleUserService {
    void doAssgin(AssginRoleDto assginRoleDto);
}
