package com.song.controller;


import com.song.biz.service.SysRoleBindMenuService;
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
 * 系统角色绑定菜单
 */
@RestController
@RequestMapping(value = "/sysRoleBindMenu")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "系统角色绑定菜单")
public class SysRoleBindMenuController {
    final SysRoleBindMenuService sysRoleBindMenuService;

    /**
     * 查询绑定的菜单列表
     *
     * @param roleId
     * @return
     */
    @Operation(summary = "查询绑定的菜单列表")
    @GetMapping(value = "/listBindMenuIdByRoleId")
    public ApiResponse<List<Integer>> listBindMenuIdByRoleId(@RequestParam int roleId) {
        return ApiResponse.success(sysRoleBindMenuService.listBindMenuIdByRoleId(roleId));
    }
}
