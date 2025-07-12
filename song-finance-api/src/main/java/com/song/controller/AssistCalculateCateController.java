package com.song.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.biz.dto.form.CreateAssistCalculateCateForm;
import com.song.biz.dto.form.DelAssistCalculateCateForm;
import com.song.biz.dto.form.UpdateAssistCalculateCateForm;
import com.song.biz.dto.vo.GetAssistCalculateCateVo;
import com.song.biz.dto.vo.ListCalculateCateVo;
import com.song.biz.service.AssistCalculateCateService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 辅助核算类别管理
 */
@RestController
@RequestMapping(value = "/assistCalculateCate")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "辅助核算类别管理")
public class AssistCalculateCateController {
    final AssistCalculateCateService assistCalculateCateService;

    /**
     * 创建辅助核算类别
     *
     * @param form
     * @return
     * @throws JsonProcessingException
     */
    @Operation(summary = "创建辅助核算类别")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateAssistCalculateCateForm form) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.create(form));
    }

    /**
     * 删除辅助核算类别
     *
     * @param form
     * @return
     */
    @Operation(summary = "删除辅助核算类别")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelAssistCalculateCateForm form) {
        return ApiResponse.success(assistCalculateCateService.del(form));
    }

    /**
     * 修改辅助核算类别
     *
     * @param form
     * @return
     * @throws JsonProcessingException
     */
    @Operation(summary = "修改辅助核算类别")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateAssistCalculateCateForm form) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.update(form));
    }

    /**
     * 查询辅助核算类别列表
     *
     * @return
     */
    @Operation(summary = "查询辅助核算类别列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListCalculateCateVo>> list() {
        return ApiResponse.success(assistCalculateCateService.list());
    }

    /**
     * 获取辅助核算类别详情
     *
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @Operation(summary = "获取辅助核算类别详情")
    @GetMapping(value = "/getById")
    public ApiResponse<GetAssistCalculateCateVo> getById(@RequestParam
                                                         @NotNull
                                                         @Range(min = 1)
                                                         Long id) throws JsonProcessingException {
        return ApiResponse.success(assistCalculateCateService.getById(id));
    }
}
