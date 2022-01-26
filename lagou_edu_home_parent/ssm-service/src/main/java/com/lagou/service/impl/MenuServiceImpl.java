package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*
     * 查询所有父子菜单信息
     * */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    /*
     * 查询所有菜单信息
     * */
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    /*
    * 回显所有的menu信息(根据ID)
    * */
    @Override
    public Menu findMenuById(Integer id) {
        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }

    /*
    * 新增菜单信息
    * */
    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        if( menu.getParentId() == -1){
            menu.setLevel(0); //父级菜单为 0
        }else{
            menu.setLevel(1); //子菜单为 1
        }

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        menuMapper.updateMenu(menu);
    }
}
