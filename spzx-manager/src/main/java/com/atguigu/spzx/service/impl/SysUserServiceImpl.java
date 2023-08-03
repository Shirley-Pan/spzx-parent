package com.atguigu.spzx.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.spzx.manager.common.config.exception.GuiguException;
import com.atguigu.spzx.manager.model.dto.system.SysUserDto;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.mapper.SysUserMapper;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.manager.model.dto.system.LoginDto;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/7/28 19:37
 * description://localstorage存储量比cookie大
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer currentPage, Integer pageSize) {
       //设置分页数据
        PageHelper.startPage(currentPage,pageSize);
        //调用mapper方法查询所有用户
        List<SysUser> sysUserList=sysUserMapper.findByPage(sysUserDto);
        //对分页数据进行封装
        PageInfo<SysUser> pageInfo =new PageInfo<>(sysUserList);
        return pageInfo;
    }
    //用户添加
    @Override
    public void saveSysUser(SysUser sysUser) {
        //对username登录名称判断，如果表存在不能添加，表不存在可以添加
        String userName = sysUser.getUserName();
        //拿着userName查询数据库
        SysUser isExistedName = sysUserMapper.selectByUserName(userName);
        if (isExistedName!=null){//名字存在不能添加
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //密码进行加密,获取输入的密码，加密
        String inputPassword = sysUser.getPassword();
        //加密密码设置回到sysUser
        String md5DigestPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        //调用mapper方法添加
        sysUser.setPassword(md5DigestPassword);
        //用户状态1 正常，需要进行设置，否则则会报错
        sysUser.setStatus(1);
        sysUserMapper.saveSysuser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteSysUser(Long userId) {
        sysUserMapper.deleteSysUser(userId);
    }


    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    //从请求头中获取token值，在service中完成
    //从redis中根据token获取用户值，把获取用户信息返回
    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:"+token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        //2.校验登录，验证码
        //获取用户输入的验证码
        String inputCode = loginDto.getCaptcha();
        //获取验证码key
        String codeKey = loginDto.getCodeKey();

        //拿着获取验证码key，查询redis，把对应验证码值查询出来
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:"+codeKey);
        //比较redis验证码和输入的是否一样
        if (StrUtil.isEmpty(redisCode)||!StrUtil.equalsIgnoreCase(redisCode,inputCode)){
                throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //校验通过，删除验证码
        //redisTemplate.delete()
        redisTemplate.delete("user:login:validatecode:"+codeKey);


        //1.从loginDTo里面获取用户名，根据用户查询数据表，得到用户名对应数据
       SysUser sysUser= sysUserMapper.selectByUserName(loginDto.getUserName());
       //如果根据用户名查询数据，返回空，直接返回登录失败，没有这个用户
        if (null==sysUser){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //如果根据用户名可以查询到数据，获取用户名对应的数据库存储的密码
        String database_password = sysUser.getPassword();//数据库密码
        String input_password = loginDto.getPassword();//输入进来的密码
        String Md5_password = DigestUtils.md5DigestAsHex(input_password.getBytes());
        //比较数据库里面存储的密码和输入的页面密码是否一样
        if (!database_password.equals(Md5_password)){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //生成token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        //把用户信息存储redis里面，key：token，value:用户信息
        //因为没有写序列化对象代码，所以需要将用户信息转换成JSON数据
        redisTemplate.opsForValue()
                .set("user:login:"+token, JSON.toJSONString(sysUser),
                        30, TimeUnit.MINUTES);
        //把token封装到loginVo，返回loginVo对象
        LoginVo loginVo =new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }



}
