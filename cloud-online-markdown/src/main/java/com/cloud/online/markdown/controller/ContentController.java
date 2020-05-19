package com.cloud.online.markdown.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.online.markdown.entity.Content;
import com.cloud.online.markdown.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * (Content)表控制层
 *
 * @author makejava
 * @since 2020-05-11 20:28:48
 */
@Controller
@RequestMapping("/contents")
@Slf4j
public class ContentController {
    /**
     * 服务对象
     */
    @Resource
    private ContentService contentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ModelAndView selectOne(@PathVariable("id") Integer id, boolean preview, Map map) {
        log.info("is preview: {}", preview);
        Content content = this.contentService.queryById(id);
        log.info("exiting content to view {}", content);
        map.put("content", content);
        if (preview) {
            return new ModelAndView("preview", map);
        }
        return new ModelAndView("edit", map);
    }

    @GetMapping("/all")
    public ModelAndView index(Map map) {
        map.put("contents", contentService.queryAllByLimit(0, 100));
        return new ModelAndView("index", map);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult<Content> update(Content incoming, @PathVariable("id") Integer id) {
        log.info("saving id {} and content {}", id, incoming);
        Content existing = contentService.queryById(id);
        if (Objects.nonNull(existing)) {
            log.info("update the existing one");
            Content content = contentService.update(incoming);
            return new CommonResult<>(HttpStatus.OK.value(), content);
        } else {
            return new CommonResult<>(HttpStatus.NOT_FOUND.value(), "数据不存在。");
        }

    }

    @PostMapping
    @ResponseBody
    public CommonResult<Content> save(Content incoming) {
        if (Objects.isNull(incoming.getId())) {
            log.info("insert a new one.");
            Content content = contentService.insert(incoming);
            return new CommonResult<>(HttpStatus.OK.value(), content);
        } else {
            return new CommonResult<>(HttpStatus.BAD_REQUEST.value(), "无效的请求。");
        }
    }

    @GetMapping
    public ModelAndView add() {
        return new ModelAndView("add");
    }
}