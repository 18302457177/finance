package com.song.controller;


import com.song.biz.dto.form.CreateVoucherWordConfigForm;
import com.song.biz.dto.form.DelVoucherWordConfigForm;
import com.song.biz.dto.form.UpdateVoucherWordConfigDefaultFlagForm;
import com.song.biz.dto.form.UpdateVoucherWordConfigForm;
import com.song.biz.dto.vo.GetVoucherWordConfigVo;
import com.song.biz.dto.vo.ListVoucherWordConfigVo;
import com.song.biz.service.VoucherWordConfigService;
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
 * 凭证字设置
 */
@RestController
@RequestMapping(value = "/voucherWordConfig")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "凭证字设置")
public class VoucherWordConfigController {
    final VoucherWordConfigService voucherWordConfigService;

    /**
     * 添加凭证字
     *
     * @param form
     * @return
     */
    @Operation(summary = "添加凭证字")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateVoucherWordConfigForm form) {
        return ApiResponse.success(voucherWordConfigService.create(form));
    }

    /**
     * 删除凭证字
     *
     * @param form
     * @return
     */
    @Operation(summary = "删除凭证字")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelVoucherWordConfigForm form) {
        return ApiResponse.success(voucherWordConfigService.del(form));
    }

    /**
     * 修改凭证字
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改凭证字")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateVoucherWordConfigForm form) {
        return ApiResponse.success(voucherWordConfigService.update(form));
    }

    /**
     * 修改默认凭证字
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改默认凭证字")
    @PostMapping(value = "/updateDefaultFlag")
    public ApiResponse<Boolean> updateDefaultFlag(@Valid @RequestBody UpdateVoucherWordConfigDefaultFlagForm form) {
        return ApiResponse.success(voucherWordConfigService.updateDefaultFlag(form));
    }

    /**
     * 获取凭证字详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "获取凭证字详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetVoucherWordConfigVo> getCurrencyConfigByCode(@RequestParam
                                                                       @NotNull
                                                                       @Range(min = 1)
                                                                       Long id) {
        return ApiResponse.success(voucherWordConfigService.getById(id));
    }

    /**
     * 查询凭证字列表
     *
     * @return
     */
    @Operation(summary = "查询凭证字列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListVoucherWordConfigVo>> list() {
        return ApiResponse.success(voucherWordConfigService.list());
    }
}
