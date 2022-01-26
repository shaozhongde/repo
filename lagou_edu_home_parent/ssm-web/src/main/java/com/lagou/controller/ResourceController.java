package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    public ResourceService resourceService;

    /*
    * 分页与条件查询
    * */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "资源信息分页多条件查询成功", pageInfo);
        return responseResult;
    }

    /*
    * 保存%修改资源
    * */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if(resource.getId()==null){
            //保存操作
            resourceService.saveResource(resource);
            ResponseResult responseResult = new ResponseResult(true, 200, "保存资源成功", null);
            return responseResult;
        }else{
            //修改操作
            resourceService.updateResource(resource);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源成功", null);
            return responseResult;
        }
    }

    /*
    * 删除资源
    * */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源成功", null);
        return responseResult;
    }
}
