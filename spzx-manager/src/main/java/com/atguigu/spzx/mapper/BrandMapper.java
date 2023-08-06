package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/4 15:54
 * description:
 */
@Mapper
@Repository
public interface BrandMapper {
     List<Brand> findByPage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);
}
