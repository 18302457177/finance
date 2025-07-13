package com.song.controller;


import com.song.biz.service.SysRoleBindResourceService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统角色绑定资源
 */
@RestController
@RequestMapping(value = "/sysRoleBindResource")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "系统角色绑定资源")
public class SysRoleBindResourceController {
    final SysRoleBindResourceService sysRoleBindResourceService;

    /**
     * 查询绑定的资源列表
     *
     * @param roleId
     * @return
     */
    @Operation(summary = "查询绑定的资源列表")
    @GetMapping(value = "/listBindResourceIdByRoleId")
    public ApiResponse<List<Integer>> listBindResourceIdByRoleId(@RequestParam int roleId) {
        return ApiResponse.success(sysRoleBindResourceService.listBindResourceIdByRoleId(roleId));
    }
}
