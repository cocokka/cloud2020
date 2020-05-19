package com.cloud.oauth2.server.controller;

import com.cloud.oauth2.server.entity.TbUser;
import com.cloud.oauth2.server.service.TbUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(TbUser)表控制层
 *
 * @author makejava
 * @since 2020-05-03 13:11:37
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbUser selectOne(Long id) {
        return this.tbUserService.queryById(id);
    }

}