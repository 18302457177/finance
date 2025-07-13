package com.song.controller;


import com.song.biz.dto.form.CreateFolderForm;
import com.song.biz.dto.form.DelForm;
import com.song.biz.dto.form.ListFolderForm;
import com.song.biz.dto.form.UpdateFolderForm;
import com.song.biz.dto.vo.GetFolderVo;
import com.song.biz.dto.vo.ListFolderVo;
import com.song.biz.service.FolderService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件夹管理
 */
@RestController
@RequestMapping(value = "/folder")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "文件夹管理")
public class FolderController {
    final FolderService folderService;

    /**
     * 文件夹列表
     *
     * @param form
     * @return
     * @throws Exception
     */
    @Operation(summary = "文件夹列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListFolderVo>> list(@Valid @ModelAttribute ListFolderForm form) throws Exception {
        return ApiResponse.success(folderService.list(form));
    }

    /**
     * 创建文件夹
     *
     * @param form
     * @return
     */
    @Operation(summary = "创建文件夹")
    @PostMapping(value = "/create")
    public ApiResponse<Long> list(@Valid @RequestBody CreateFolderForm form) {
        return ApiResponse.success(folderService.create(form));
    }

    /**
     * 修改文件夹
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改文件夹")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateFolderForm form) {
        return ApiResponse.success(folderService.update(form));
    }

    /**
     * 删除文件夹
     *
     * @param form
     * @return
     */
    @Operation(summary = "删除文件夹")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelForm form) {
        return ApiResponse.success(folderService.del(form));
    }

    /**
     * 获取文件夹详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "获取文件夹详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetFolderVo> get(@Valid @RequestParam("id") Long id) {
        return ApiResponse.success(folderService.get(id));
    }
}
