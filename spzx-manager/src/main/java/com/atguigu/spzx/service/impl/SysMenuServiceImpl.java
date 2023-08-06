package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.utils.AuthContextUtil;
import com.atguigu.spzx.manager.common.config.exception.GuiguException;
import com.atguigu.spzx.manager.model.entity.system.SysMenu;
import com.atguigu.spzx.manager.model.entity.system.SysUser;
import com.atguigu.spzx.manager.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.manager.model.vo.system.SysMenuVo;
import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * projectName: com.atguigu.spzx.service.impl
 *
 * @author: ppp
 * time: 2023/8/3 10:29
 * description:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodesMenu() {
        //查询所有菜单
        //把查询出来的所有菜单list集合，转换要求的数据格式
        //使用递归实现，第一个原则，入口 parentid=0
        //从第一个结点开始操作，找到第一个结点下面子结点
        //判断条件，谁的parentId=当前结点id，就是他的子节点
        //第二个原则，结束条件
        //查询所有菜单
        List<SysMenu> sysMenuList=sysMenuMapper.selectAllMenu();
        //遇到集合不是return就是遍历，将便利的过程放到了工具类中的方法中
        if (CollectionUtils.isEmpty(sysMenuList))return null;
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);//构建树形数据
        return treeList;


    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);

    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count =sysMenuMapper.countByParentId(id);//先查询是否存在子菜单，如果存在不允许进行删除
        if (count>0){
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.removeById(id);

    }

    @Override
    public List<SysMenuVo> findUserMenuList() {
        //获取当前登录用户的id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        //根据用户id查询可以操作菜单
        List<SysMenu> sysMenuList=sysMenuMapper.selectMenuListByUserId(userId);
        //封装路由要求格式

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }


}
