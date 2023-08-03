package com.atguigu.spzx.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/7/30 15:48
 * description:
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    //需要把验证码存到redis中
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode() {
        //使用hutool工具包中的工具类生成图片验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 3);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        //生成UUID作为图片验证码的key
        String codeKey = UUID.randomUUID().toString().replace("-", "");
        //将验证码存储到redis中
        redisTemplate.opsForValue().set("user:login:validatecode:"+codeKey,codeValue,5, TimeUnit.MINUTES);

        //将数据响应给前端
        ValidateCodeVo validateCodeVo =new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);
        return validateCodeVo;
    }
}
