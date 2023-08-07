package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.manager.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/6 9:07
 * description:
 */
@Mapper
@Repository
public interface CategoryBrandMapper {
    List<CategoryBrand> findPage(CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}
