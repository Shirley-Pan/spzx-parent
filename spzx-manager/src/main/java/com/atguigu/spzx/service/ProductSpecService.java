package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/6 11:30
 * description:
 */
@Service
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void removeById(Long id);
}
