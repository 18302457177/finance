package com.song.biz.dto.form;



import com.song.common.dto.PageHelperRequest;
import lombok.Data;

@Data
public class ListRoleForm extends PageHelperRequest {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否禁用；true：禁用；false：启用
     */
    private Boolean disable;
}
