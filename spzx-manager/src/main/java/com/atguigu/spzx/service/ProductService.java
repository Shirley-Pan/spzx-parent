package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.dto.product.ProductDto;
import com.atguigu.spzx.manager.model.entity.product.Product;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/6 15:11
 * description:
 */
@Service
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);
}
