package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.mysql.fabric.Response;

import java.util.List;

public interface UserService {
    /*
    * 用户分页和多条件组合查询
    * */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
    * 更新用户状态
    * */
    public void updateUserStatus(int id,String status);

    /*
    * 用户登录
    * */
    public User login(User user) throws Exception;

    /*
    * 根据用户ID查询关联的角色信息 (分配角色：回显)
    * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 用户关联角色(先根据用户userid清空中间表关联关系,再分配角色)
    * */
    public void userContextRole(UserVo userVo);

    /*
    * 获取用户权限 进行菜单动态展示
    * */
    public ResponseResult getUserPermissions(Integer userid);
}
