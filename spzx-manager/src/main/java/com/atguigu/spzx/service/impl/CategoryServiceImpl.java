package com.atguigu.spzx.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.common.config.exception.GuiguException;
import com.atguigu.spzx.manager.model.entity.product.Category;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.manager.model.vo.product.CategoryExcelVo;
import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import listener.MyReadListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/4 9:42
 * description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void exportData(HttpServletResponse response) {

        try {
        //设置mime类型,下载文件格式内容
        response.setContentType("application/vnd.ms-excel");
        //设置编码方式
        response.setCharacterEncoding("UTF-8");
        //设置响应头信息 ,设置响应头信息content-disposition,意思是以文件的方式下载
            String fileName= URLEncoder.encode("分类管理","utf-8");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            //设置文件下载名称

            //从数据库查出所有分类
           List<Category> allCategoryList= categoryMapper.selectAllCategory();

           //List<category>-->List<CategoryExcelVo>
            List<CategoryExcelVo> categoryExcelVoList=new ArrayList<>();
            for (Category category : allCategoryList) {
                CategoryExcelVo categoryExcelVo =new CategoryExcelVo();
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            }

            // 从easyExcel把查询出来的所有分类list写到表格中
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据")
                    .doWrite(allCategoryList);

        } catch (Exception e) {
            throw new GuiguException(ResultCodeEnum.SYSTEM_ERROR);
        }

    }

    @Override
    public void importData(MultipartFile file) {

        try {
            //创建监听器对象，传递mapper对象
            MyReadListener<CategoryExcelVo> excelVoMyReadListener =new MyReadListener<>(categoryMapper);

            EasyExcel.read(file.getInputStream(),
                    CategoryExcelVo.class,
                    excelVoMyReadListener).sheet().doRead();
        } catch (IOException e) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }


    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        // 根据分类id查询它下面的所有的子分类数据
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)) {

            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {

                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }

            });
        }
        return categoryList;
    }
}
