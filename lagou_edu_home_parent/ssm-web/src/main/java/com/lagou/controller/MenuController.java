package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
     * 查询所有菜单信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
        return responseResult;
    }

    /*
    * 回显菜单信息
    * */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //根据ID的值判断当前是更新还是添加操作 判断ID的值是否为-1
        if(id==-1){
            //添加操作 回显信息中不需要menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<Object, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加回显成功", map);
            return responseResult;
        }else{
            //修改操作 回显所有的menu信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<Object, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult responseResult = new ResponseResult(true, 200, "修改回显成功", map);
            return responseResult;
        }
    }

    /**
     * 新建&修改菜单
     * */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId() != null){
            //修改操作
            menuService.updateMenu(menu);
            ResponseResult responseResult = new ResponseResult(true,200,"响应成功",null);
            return responseResult;
        }else {
            //新增操作
            menuService.saveMenu(menu);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        }
    }
}
