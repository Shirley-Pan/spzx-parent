package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/3 11:49
 * description:
 */
@RestController
@RequestMapping("admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("fileUpload")
    public Result fileUploadService(MultipartFile file){
        String fileUrl=fileUploadService.fileUpload(file);
        return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    }
}
