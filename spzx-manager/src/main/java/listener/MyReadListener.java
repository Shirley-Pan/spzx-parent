package listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.manager.model.vo.product.CategoryExcelVo;
import com.atguigu.spzx.mapper.CategoryMapper;

import java.util.List;

/**
 * projectName: listener
 *
 * @author: ppp
 * time: 2023/8/4 14:49
 * description:
 */

public class MyReadListener<T> extends AnalysisEventListener<T> {

    private static final int BATCH_COUNT=5;
    /**
     * 缓存数据
     */
    private List<CategoryExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //通过构造器导入mapper
    private CategoryMapper categoryMapper;
    public MyReadListener (CategoryMapper categoryMapper) {
        this.categoryMapper=categoryMapper;
    }

    //一行一行读取信息，将读取的信息一行一行的封装到t对象之中
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        CategoryExcelVo categoryExcelVo =(CategoryExcelVo)t;
        //将读取的每行数据放到缓存集合中
        cachedDataList.add(categoryExcelVo);
        //缓存达到设置值，调用方法一次性添加数据库
        if (cachedDataList.size()>=BATCH_COUNT){
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel解析完毕以后需要执行的代码
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    private void saveData() {
        categoryMapper.batchInsert(cachedDataList);
    }
}
