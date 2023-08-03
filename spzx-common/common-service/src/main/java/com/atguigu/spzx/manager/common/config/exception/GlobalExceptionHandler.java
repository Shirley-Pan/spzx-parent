package com.atguigu.spzx.manager.common.config.exception;

import com.atguigu.spzx.manager.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * projectName: com.atguigu.spzx.manager.common.config.exception
 *
 * @author: ppp
 * time: 2023/7/31 20:50
 * description:统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null,201,"出现了异常");
    }

    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    public Result error(GuiguException exception){
        exception.printStackTrace();
        return Result.build(null,exception.getResultCodeEnum());
    }



}
