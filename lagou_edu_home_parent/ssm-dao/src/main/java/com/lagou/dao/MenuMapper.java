package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /*
    * 查询所有父子菜单信息 (最后写在RoleController)
    * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
    * 查询所有菜单信息
    * */
    public List<Menu> findAllMenu();

    /*
     * 查询所有菜单信息(根据ID)
     * */
    public Menu findMenuById(Integer id);

    /**
     * 新增菜单
     * */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单
     * */
    public void updateMenu(Menu menu);
}
