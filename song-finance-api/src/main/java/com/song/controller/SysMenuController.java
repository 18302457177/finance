package com.song.controller;


import com.song.biz.dto.form.CreateMenuForm;
import com.song.biz.dto.form.DelMenuForm;
import com.song.biz.dto.form.UpdateMenuForm;
import com.song.biz.dto.vo.GetMenuByIdVo;
import com.song.biz.dto.vo.ListTreeMenuVo;
import com.song.biz.dto.vo.ListTreeSelectMenuVo;
import com.song.biz.service.SysMenuService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单管理
 */
@RestController
@RequestMapping(value = "/sysMenu")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "系统菜单管理")
public class SysMenuController {
    final SysMenuService sysMenuService;

    /**
     * 树形菜单列表
     *
     * @param title
     * @return
     */
    @Operation(summary = "树形菜单列表")
    @GetMapping(value = "/listTreeMenu")
    public ApiResponse<List<ListTreeMenuVo>> listTreeMenu(@RequestParam(required = false) String title) {
        return ApiResponse.success(sysMenuService.listTreeMenu(title));
    }

    /**
     * 树形选择菜单列表
     *
     * @return
     */
    @Operation(summary = "树形选择菜单列表")
    @GetMapping(value = "/listTreeSelectMenu")
    public ApiResponse<List<ListTreeSelectMenuVo>> listTreeSelectMenu() {
        return ApiResponse.success(sysMenuService.listTreeSelectMenu());
    }

    /**
     * 获取菜单明细
     *
     * @param id
     * @return
     */
    @Operation(summary = "获取菜单明细")
    @GetMapping(value = "/getMenuById")
    public ApiResponse<GetMenuByIdVo> getMenuById(@Validated @RequestParam @NotNull Integer id) {
        return ApiResponse.success(sysMenuService.getMenuById(id));
    }

    /**
     * 创建菜单
     *
     * @param form
     * @return
     */
    @Operation(summary = "创建菜单")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Validated @RequestBody CreateMenuForm form) {
        return ApiResponse.success(sysMenuService.create(form));
    }

    /**
     * 修改菜单
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改菜单")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Validated @RequestBody UpdateMenuForm form) {
        return ApiResponse.success(sysMenuService.update(form));
    }

    /**
     * 删除菜单
     *
     * @param form
     * @return
     */
    @Operation(summary = "删除菜单")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Validated @RequestBody DelMenuForm form) {
        return ApiResponse.success(sysMenuService.del(form));
    }
}
