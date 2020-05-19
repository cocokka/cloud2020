package com.cloud.oauth2.client.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.oauth2.client.entity.TbUser;
import com.cloud.oauth2.client.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表(TbUser)表控制层
 *
 * @author makejava
 * @since 2020-05-03 18:12:40
 */
@Api("USERS")
@RestController
@RequestMapping("user")
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
    @ApiOperation("文章列表")
    @GetMapping("view/{id}")
    public CommonResult<TbUser> selectOne(@PathVariable("id") Long id) {
        return new CommonResult<>(HttpStatus.OK.value(), this.tbUserService.queryById(id));
    }

}