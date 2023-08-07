package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.manager.model.entity.product.CategoryBrand;
import com.atguigu.spzx.mapper.CategoryBrandMapper;
import com.atguigu.spzx.service.CategoryBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/6 9:07
 * description:
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findPageCatehgoryBrand(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        //设置分页参数
        PageHelper.startPage(page,limit);
        //调mapper中的方法查询所有
        List<CategoryBrand> list =categoryBrandMapper.findPage(categoryBrandDto);
        //使用pageInfo构建分页返回数据
        PageInfo<CategoryBrand> pageInfo =new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }
}
