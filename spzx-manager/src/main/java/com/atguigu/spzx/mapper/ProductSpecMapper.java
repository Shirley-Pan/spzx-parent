package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.map.repository.config.EnableMapRepositories;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/6 11:29
 * description:
 */
@Mapper
public interface ProductSpecMapper {
    List<ProductSpec> findByPage();

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deletedById(Long id);
}
