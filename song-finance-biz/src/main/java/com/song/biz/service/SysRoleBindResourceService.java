package com.song.biz.service;



import com.song.biz.dto.form.RoleBindResourceForm;

import java.util.List;

public interface SysRoleBindResourceService {
    /**
     * 角色绑定资源列表
     * @param form
     * @return
     */
    boolean roleBindResource(RoleBindResourceForm form);

    /**
     * 查询绑定的资源列表
     * @param roleId
     * @return
     */
    List<Integer> listBindResourceIdByRoleId(int roleId);
}
