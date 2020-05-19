package com.cloud.online.markdown.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Content)实体类
 *
 * @author makejava
 * @since 2020-05-11 20:28:41
 */
@Data
public class Content implements Serializable {
    private static final long serialVersionUID = -20066842291496344L;

    private Integer id;

    private String title;

    private String text;
    
    private String textContent;

}