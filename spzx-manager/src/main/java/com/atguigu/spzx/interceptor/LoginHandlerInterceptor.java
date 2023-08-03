package com.atguigu.spzx.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.utils.AuthContextUtil;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * projectName: com.atguigu.spzx.interceptor
 *
 * @author: ppp
 * time: 2023/7/29 15:39
 * description:
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求方式，如果是option预检请求，直接放行
        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            return true;
        }
        //从请求头获取token
        String token = request.getHeader("token");
        //如果请求头里面获取不到token值，当前不是登录，直接提示错误信息
        if (StrUtil.isEmpty(token)){
            responseNoLoginInfo(response);
            return false;
        }
        //如果请求头获取到token，拿着token查询redis
        String userInfoJson = redisTemplate.opsForValue().get("user:login:" + token);

        //如果查询redis，查不到，没有登录，直接提示错误信息，return false
        if (StrUtil.isEmpty(userInfoJson)){
            responseNoLoginInfo(response);
            return false;
        }
        //如果查询redis,查询到了数据，是登录
        SysUser sysUser = JSON.parseObject(userInfoJson, SysUser.class);

        //把查询redis获取用户信息放到ThreadLocal里面
        AuthContextUtil.set(sysUser);
        //延长redis过期时间
        redisTemplate.expire("user:login:"+token,30, TimeUnit.MINUTES);
        return true;
    }

    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        AuthContextUtil.remove();//移除threadLocal中的数据
    }
}
