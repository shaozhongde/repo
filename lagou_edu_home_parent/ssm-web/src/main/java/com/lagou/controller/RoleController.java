package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /*
    * 查询所有角色和条件进行查询
    * */
    @RequestMapping("findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
        return responseResult;
    }

    /*
    * 添加角色+更新角色
    * */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if(role.getId()==null){
            //添加角色
            roleService.saveRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加角色成功", null);
            return responseResult;
        }else{
            //更新成功
            roleService.updateRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新角色成功", null);
            return responseResult;
        }
    }
    /*
     * 查询所有父子菜单信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //-1表示查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单成功", map);
        return responseResult;
    }

    /*
     * 根据角色ID查询该角色关联的菜单信息ID
     * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRoleId);
        return responseResult;
    }

    /*
    * 根据roleid清空中间表的关联关系+为角色分配菜单
    * */
    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;
    }

    /*
    * 根据roleid清空中间表的关联关系+删除角色
    * */
    @RequestMapping("deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }

    /*
    * 获取当前角色拥有的 资源信息(包括资源分类以及资源信息)
    * */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(int roleId){

        //1.获取角色拥有的资源分类信息
        List<ResourceCategory> categoryList = roleService.findRoleHaveResource(roleId);

        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",categoryList);
        return responseResult;
    }

    /*
    * 角色分配资源(先删除角色和资源的关联信息)
    * */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",null);
        return responseResult;
    }
}
