package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {
    /*
    * 查询所有角色和条件进行查询
    * */
    public List<Role> findAllRole(Role role);

    /*
    * 添加角色
    * */
    public void saveRole(Role role);

    /*
    * 更新角色
    * */
    public void updateRole(Role role);

    /*
     * 根据角色ID查询该角色关联的菜单信息ID
     * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
     * 根据roleid清空中间表的关联关系+为角色分配菜单信息
     * */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleid);

    /*
     * 查询当前角色拥有的资源分类信息
     * */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);

    /*
     * 查询当前角色拥有的资源信息
     * */
    public List<Resource> findResourceByRoleId(Integer roleId);

    /*
    * 获取角色拥有的资源分类数据+获取角色拥有的资源数据
    * */
    public List<ResourceCategory> findRoleHaveResource(int id);

    /*
    * 为角色分配资源(先删除角色和资源的关联信息)
    * */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
