package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    /*
     * 查询所有父子菜单信息
     * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
     * 查询所有菜单信息
     * */
    public List<Menu> findAllMenu();

    /*
    * 回显所有的menu信息(根据ID)
    * */
    public Menu findMenuById(Integer id);

    /*
    * 新增菜单信息
    * */
    public void saveMenu(Menu menu);

    /*
    * 修改菜单信息
    * */
    public void updateMenu(Menu menu);
}
