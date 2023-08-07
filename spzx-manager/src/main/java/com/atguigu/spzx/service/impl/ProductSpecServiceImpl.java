package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.manager.model.entity.product.ProductSpec;
import com.atguigu.spzx.mapper.ProductSpecMapper;
import com.atguigu.spzx.service.ProductSpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/6 11:29
 * description:
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductSpec> productSpecList=productSpecMapper.findByPage();
        PageInfo<ProductSpec> pageInfo =new PageInfo<>(productSpecList);
        return pageInfo;
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void removeById(Long id) {
        productSpecMapper.deletedById(id);
    }


}
