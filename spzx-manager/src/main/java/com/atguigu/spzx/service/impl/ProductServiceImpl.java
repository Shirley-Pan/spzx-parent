package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.dto.product.ProductDto;
import com.atguigu.spzx.manager.model.entity.product.Product;
import com.atguigu.spzx.mapper.ProductMapper;
import com.atguigu.spzx.service.ProductService;
import com.atguigu.spzx.service.ProductSpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/6 15:10
 * description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page,limit);
        List<Product> productList=productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo =new PageInfo<>(productList);
        return pageInfo;
    }
}
