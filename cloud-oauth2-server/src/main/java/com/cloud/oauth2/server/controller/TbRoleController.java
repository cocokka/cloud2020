package com.cloud.oauth2.server.controller;

import com.cloud.oauth2.server.entity.TbRole;
import com.cloud.oauth2.server.service.TbRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(TbRole)表控制层
 *
 * @author makejava
 * @since 2020-05-03 14:01:38
 */
@RestController
@RequestMapping("tbRole")
public class TbRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TbRoleService tbRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbRole selectOne(Long id) {
        return this.tbRoleService.queryById(id);
    }

}