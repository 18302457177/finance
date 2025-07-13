package com.song.controller;


import com.song.biz.dto.form.CreateCurrencyConfigForm;
import com.song.biz.dto.form.DelCurrencyConfigForm;
import com.song.biz.dto.form.UpdateCurrencyConfigForm;
import com.song.biz.dto.vo.GetCurrencyConfigVo;
import com.song.biz.dto.vo.ListCurrencyConfigVo;
import com.song.biz.service.CurrencyConfigService;
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
 * 币别设置
 */
@RestController
@RequestMapping(value = "/currencyConfig")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "币别设置")
public class CurrencyConfigController {
    final CurrencyConfigService currencyConfigService;

    /**
     * 添加币别
     *
     * @param form
     * @return
     */
    @Operation(summary = "添加币别")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.create(form));
    }

    /**
     * 删除币别
     *
     * @param form
     * @return
     */
    @Operation(summary = "删除币别")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.del(form));
    }

    /**
     * 修改币别
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改币别")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateCurrencyConfigForm form) {
        return ApiResponse.success(currencyConfigService.update(form));
    }

    /**
     * 获取币别详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "获取币别详情")
    @GetMapping(value = "/get")
    public ApiResponse<GetCurrencyConfigVo> getById(
            @Validated
            @RequestParam
            @NotNull
            @Range(min = 1)
            Long id) {
        return ApiResponse.success(currencyConfigService.getById(id));
    }

    /**
     * 查询币别列表
     *
     * @return
     */
    @Operation(summary = "查询币别列表")
    @GetMapping(value = "/list")
    public ApiResponse<List<ListCurrencyConfigVo>> list() {
        return ApiResponse.success(currencyConfigService.list());
    }
}
