package com.atguigu.spzx.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/3 11:56
 * description:
 */
@Service
public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
