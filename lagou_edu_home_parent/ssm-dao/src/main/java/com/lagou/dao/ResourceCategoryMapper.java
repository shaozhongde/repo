package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*
    * 查询所有资源分类
    * */
    public List<ResourceCategory> findAllResourceCategory();

    /*
    * 添加资源分类
    * */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /*
    * 更新资源分类
    * */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /*
    * 删除资源分类
    * */
    public void deleteResourceCategory(Integer id);

    /*
    *  获取当前角色拥有的资源信息(写在RoleController)
    * */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);
}
