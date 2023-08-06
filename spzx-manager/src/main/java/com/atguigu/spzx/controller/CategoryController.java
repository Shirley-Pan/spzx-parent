package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.entity.product.Category;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/4 9:40
 * description:
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value = "/findByParentId/{parentId}")
    public Result<List<Category>> findByParentId(@PathVariable Long parentId) {
        List<Category> list = categoryService.findByParentId(parentId);
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    //导入分类
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        categoryService.importData(file);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //导出分类
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        categoryService.exportData(response);
    }
}
