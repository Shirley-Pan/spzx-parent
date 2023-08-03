package com.atguigu.spzx.common.utils;

import com.atguigu.spzx.manager.model.entity.system.SysUser;

/**
 * projectName: com.atguigu.spzx.common.utils
 *
 * @author: ppp
 * time: 2023/7/29 15:36
 * description:
 */

public class AuthContextUtil {
    //创建threadlocal 对象
    private  static  final  ThreadLocal<SysUser> threadLocal =new ThreadLocal<>();
    //添加值
    public  static  void set(SysUser sysUser){
        threadLocal.set(sysUser);
    }
    //获取值
    public static  SysUser get(){
        SysUser sysUser = threadLocal.get();
        return sysUser;
    }
    //删除值
    public static  void remove(){
        threadLocal.remove();
    }
}
