package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.dto.product.ProductDto;
import com.atguigu.spzx.manager.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/6 15:09
 * description:
 */
@Mapper
public interface ProductMapper {
    List<Product> findByPage(ProductDto productDto);
}
