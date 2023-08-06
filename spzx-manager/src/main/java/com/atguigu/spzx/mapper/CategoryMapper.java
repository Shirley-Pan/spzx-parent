package com.atguigu.spzx.mapper;

import com.atguigu.spzx.manager.model.entity.product.Category;
import com.atguigu.spzx.manager.model.vo.product.CategoryExcelVo;
import com.atguigu.spzx.service.CategoryService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * projectName: com.atguigu.spzx.mapper
 *
 * @author: ppp
 * time: 2023/8/4 9:40
 * description:
 */
@Mapper
@Repository
public interface CategoryMapper {

    List<Category> selectAllCategory();

    int countByParentId(Long id);

    List<Category> selectByParentId(Long parentId);

    void batchInsert(List<CategoryExcelVo> cachedDataList);
}
