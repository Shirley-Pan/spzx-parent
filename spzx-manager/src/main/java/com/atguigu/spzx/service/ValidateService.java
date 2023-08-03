package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.vo.system.ValidateCodeVo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/7/30 15:48
 * description:
 */
@Service
public interface ValidateService {
     ValidateCodeVo generateValidateCode();

}
