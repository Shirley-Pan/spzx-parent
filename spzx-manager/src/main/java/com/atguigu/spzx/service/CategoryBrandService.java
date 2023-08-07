package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.manager.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/6 9:09
 * description:
 */
@Service
public interface CategoryBrandService {



    PageInfo<CategoryBrand> findPageCatehgoryBrand(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}
