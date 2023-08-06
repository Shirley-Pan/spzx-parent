package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/4 9:40
 * description:
 */
@Service
public interface CategoryService {
    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

    List<Category> findByParentId(Long parentId);
}
