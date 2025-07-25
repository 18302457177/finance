package com.song.controller;


import com.song.biz.dto.vo.DataDictionaryVo;
import com.song.biz.service.DataDictionaryService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据字典
 */
@RestController
@RequestMapping(value = "/DataDictionary")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "数据字典")
public class DataDictionaryController {
    final DataDictionaryService dataDictionaryService;

    /**
     * 查询行业数据列表
     *
     * @return
     */
    @Operation(summary = "查询行业数据列表")
    @GetMapping(value = "/listHangYe")
    public ApiResponse<List<DataDictionaryVo>> listHangYe() {
        return ApiResponse.success(dataDictionaryService.listHangYe());
    }

    /**
     * 查询会计准则数据列表
     *
     * @return
     */
    @Operation(summary = "查询会计准则数据列表")
    @GetMapping(value = "/listKuaiJiZhunZe")
    public ApiResponse<List<DataDictionaryVo>> listKuaiJiZhunZe() {
        return ApiResponse.success(dataDictionaryService.listKuaiJiZhunZe());
    }
}
