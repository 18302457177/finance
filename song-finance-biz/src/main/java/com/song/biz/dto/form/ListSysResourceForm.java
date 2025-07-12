package com.song.biz.dto.form;


import lombok.Data;

/**
 * 获取资源
 */
@Data
public class ListSysResourceForm {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String path;
}
