package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
    * 查询所有资源分类
    * */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有分类信息成功", allResourceCategory);
        return responseResult;
    }

    /*
     * 添加资源分类
     * */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if(resourceCategory.getId()!=null){
            //更新资源分类
            resourceCategoryService.updateResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新资源分类成功", null);
            return responseResult;
        }else{
            //添加资源分类
            resourceCategoryService.saveResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源分类成功", null);
            return responseResult;
        }
    }

    /*
    * 删除资源分类
    * */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源分类成功", null);
        return responseResult;
    }

}
