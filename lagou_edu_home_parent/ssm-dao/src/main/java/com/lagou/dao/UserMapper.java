package com.lagou.dao;


import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*
    * 用户分页和多条件组合查询
    * */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
    * 更新用户状态
    * */
    public void updateUserStatus(User user);

    /*
    * 用户登录(根据用户名查询具体的用户信息)
    * */
    public User login(User user);

    /*
    * 根据用户ID查询关联的角色信息
    * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 根据用户userid清空中间表关联关系
    * */
    public void deleteUserContextRole(Integer userid);

    /*
    * 分配角色
    * */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
    * 获取用户权限 进行菜单动态展示 四步走
    * 1.根据用户ID查询关联的角色信息
    public List<Role> findUserRelationRoleById(Integer id);
    * */

    /*
    * 2.根据角色ID查询角色所拥有的顶级菜单（-1）
    * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
    * 3.根据PID查询子菜单信息
    * */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
    * 4.获取用户拥有的资源权限信息
    * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
