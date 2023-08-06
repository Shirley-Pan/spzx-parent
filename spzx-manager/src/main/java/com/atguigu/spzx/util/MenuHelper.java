package com.atguigu.spzx.util;

import com.atguigu.spzx.manager.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName: com.atguigu.spzx.util
 *
 * @author: ppp
 * time: 2023/8/3 10:45
 * description:
 */

public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){

        //查询所有菜单
        //把查询出来的所有菜单list集合，转换要求的数据格式
        //判断条件，谁的parentId=当前结点id，就是他的子节点
        //第二个原则，结束条件
        // 创建list集合，用于封装最终数据
        List<SysMenu> trees =new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu:sysMenuList) {
            //使用递归实现，第一个原则，入口 parentid=0
            if (sysMenu.getParentId().longValue()==0){
                //从第一个结点开始操作，找到第一个结点下面子结点
                //sysMenu根节点，sysMenuList所有菜单。到所有菜单list集合，找到根节点下面子节点
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //sysmenu 里面子节点集合初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单集合
        for (SysMenu it:sysMenuList){
            //判断当前结点id和其他节点parentid 相同，就是他的子节点
            if (sysMenu.getId()== it.getParentId().longValue()){
                //把得到的子节点设置到当前结点里面
                sysMenu.getChildren().add(findChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }


    //从根节点开始找，
}
