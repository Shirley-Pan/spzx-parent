package com.atguigu.spzx.service;

import com.atguigu.spzx.manager.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * projectName: com.atguigu.spzx.service
 *
 * @author: ppp
 * time: 2023/8/4 15:53
 * description:
 */
@Service
public interface BrandService {

    PageInfo<Brand> list(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);
}
