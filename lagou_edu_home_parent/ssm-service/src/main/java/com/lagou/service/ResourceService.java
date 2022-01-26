package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    /*
     * 资源分页和多条件查询
     * */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);

    /*
    * 保存资源
    * */
    public void saveResource(Resource resource);

    /*
    * 修改资源
    * */
    public void updateResource(Resource resource);

    /*
    * 删除资源
    * */
    public void deleteResource(Integer id);
}
