package com.atguigu.spzx.manager.common.config.exception;

import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * projectName: com.atguigu.spzx.manager.common.config.exception
 *
 * @author: ppp
 * time: 2023/7/31 21:00
 * description:
 */
@Data
public class GuiguException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public GuiguException( ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }

}
