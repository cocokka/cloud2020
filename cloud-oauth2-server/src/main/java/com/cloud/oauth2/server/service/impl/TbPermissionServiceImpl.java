package com.cloud.oauth2.server.service.impl;

import com.cloud.oauth2.server.dao.TbPermissionMapper;
import com.cloud.oauth2.server.entity.TbPermission;
import com.cloud.oauth2.server.service.TbPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(TbPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-05-03 14:01:31
 */
@Service("tbPermissionService")
public class TbPermissionServiceImpl implements TbPermissionService {
    @Resource
    private TbPermissionMapper tbPermissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbPermission queryById(Long id) {
        return this.tbPermissionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TbPermission> queryAllByLimit(int offset, int limit) {
        return this.tbPermissionMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbPermission 实例对象
     * @return 实例对象
     */
    @Override
    public TbPermission insert(TbPermission tbPermission) {
        this.tbPermissionMapper.insert(tbPermission);
        return tbPermission;
    }

    /**
     * 修改数据
     *
     * @param tbPermission 实例对象
     * @return 实例对象
     */
    @Override
    public TbPermission update(TbPermission tbPermission) {
        this.tbPermissionMapper.update(tbPermission);
        return this.queryById(tbPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbPermissionMapper.deleteById(id) > 0;
    }

    @Override
    public List<TbPermission> queryByUserId(Long id) {
        return tbPermissionMapper.queryByUserId(id);
    }
}