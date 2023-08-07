package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.manager.model.entity.product.Category;
import com.atguigu.spzx.manager.model.entity.product.CategoryBrand;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.CategoryBrandService;
import com.github.pagehelper.PageInfo;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/6 9:06
 * description:
 */
@RestController
@RequestMapping
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    //品牌分页条件查询
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Integer page,
                        @PathVariable Integer limit,
                        CategoryBrandDto categoryBrandDto){
        PageInfo<CategoryBrand> pageInfo=categoryBrandService.findPageCatehgoryBrand(page,limit,categoryBrandDto);
        return  Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("save")
    public Result save(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.save(categoryBrand);
        return  Result.build(null, ResultCodeEnum.SUCCESS);
    }
    //修改
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
