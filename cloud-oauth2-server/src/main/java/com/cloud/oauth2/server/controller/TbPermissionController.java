package com.cloud.oauth2.server.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.oauth2.server.entity.TbPermission;
import com.cloud.oauth2.server.service.TbPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(TbPermission)表控制层
 *
 * @author makejava
 * @since 2020-05-03 14:01:31
 */
@RestController
@RequestMapping("tbPermission")
public class TbPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private TbPermissionService tbPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbPermission selectOne(Long id) {
        return this.tbPermissionService.queryById(id);
    }

    @GetMapping("permissions/user/{id}")
    public CommonResult getPermissions(@PathVariable("id") Long id) {
        return new CommonResult<>(200, this.tbPermissionService.queryByUserId(id));
    }

}