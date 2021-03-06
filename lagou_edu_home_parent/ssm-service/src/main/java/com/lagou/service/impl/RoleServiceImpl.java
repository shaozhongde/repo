package com.lagou.service.impl;


import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*
    * 查询所有角色和条件进行查询
    * */
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    /*
    * 添加角色
    * */
    @Override
    public void saveRole(Role role) {
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    /*
    * 更新角色
    * */
    @Override
    public void updateRole(Role role) {
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //2.为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleid) {

        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);
        //再进行删除角色
        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId) {
        //查询当前角色拥有的资源分类信息
        List<ResourceCategory> list = roleMapper.findResourceCategoryByRoleId(roleId);
        return list;
    }

    @Override
    public List<Resource> findResourceByRoleId(Integer roleId) {
        List<Resource> list = roleMapper.findResourceByRoleId(roleId);
        return list;
    }

    @Override
    public List<ResourceCategory> findRoleHaveResource(int id) {

        //1.获取角色拥有的资源分类数据
        List<ResourceCategory> categoryList = roleMapper.findRoleHaveResourceCate(id);

        //2.获取角色拥有的资源数据
        List<Resource> resourceList = roleMapper.findRoleHaveResource(id);
        //3.将资源数据封装到对应分类下
        for (ResourceCategory category : categoryList) {
            for (Resource resource : resourceList) {
                //判断
                if(category.getId() == resource.getCategoryId()){
                    //将资源保存到集合中
                    category.setResourceList(resourceList);
                }
            }
        }
        //4.返回资源分类集合
        return categoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        //根据角色id 清空中间表
        Integer roleId = roleResourceVo.getRoleId();
        roleMapper.deleteRoleContextResource(roleId);

        //获取分配资源的id集合
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        //向中间表插入最新的关联信息
        for (Integer resId : resourceIdList) {
            RoleResourceRelation relation = new RoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resId);
            Date date = new Date();
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedBy("system");

            roleMapper.roleContextResource(relation);
        }
    }
}
