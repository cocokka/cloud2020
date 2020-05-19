package com.cloud.online.markdown.service.impl;

import com.cloud.online.markdown.dao.ContentMapper;
import com.cloud.online.markdown.entity.Content;
import com.cloud.online.markdown.service.ContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Content)表服务实现类
 *
 * @author makejava
 * @since 2020-05-11 20:28:47
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Content queryById(Integer id) {
        return this.contentMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Content> queryAllByLimit(int offset, int limit) {
        return this.contentMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param content 实例对象
     * @return 实例对象
     */
    @Override
    public Content insert(Content content) {
        this.contentMapper.insert(content);
        return content;
    }

    /**
     * 修改数据
     *
     * @param content 实例对象
     * @return 实例对象
     */
    @Override
    public Content update(Content content) {
        this.contentMapper.update(content);
        return this.queryById(content.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.contentMapper.deleteById(id) > 0;
    }
}