package com.atguigu.spzx.controller;

import com.atguigu.spzx.manager.model.entity.product.ProductSpec;
import com.atguigu.spzx.manager.model.vo.common.Result;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.ProductSpecService;
import com.github.pagehelper.PageInfo;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName: com.atguigu.spzx.controller
 *
 * @author: ppp
 * time: 2023/8/6 11:29
 * description:
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    private ProductSpecService productSpecService;
 //商品规格分页查询功能
    @GetMapping("{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer limit){
        PageInfo<ProductSpec> pageInfo=productSpecService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec){
        productSpecService.save(productSpec);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //修改
    @PutMapping("updateById")
    public Result updateById(ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        productSpecService.removeById(id);
        return  Result.build(null,ResultCodeEnum.SUCCESS);

    }

}
