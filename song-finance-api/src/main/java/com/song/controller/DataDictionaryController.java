package com.song.controller;


import com.song.biz.dto.vo.DataDictionaryVo;
import com.song.biz.service.DataDictionaryService;
import com.song.common.dto.ApiResponse;
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
public class DataDictionaryController {
    final DataDictionaryService dataDictionaryService;

    /**
     * 查询行业数据列表
     *
     * @return
     */
    @GetMapping(value = "/listHangYe")
    public ApiResponse<List<DataDictionaryVo>> listHangYe() {
        return ApiResponse.success(dataDictionaryService.listHangYe());
    }

    /**
     * 查询会计准则数据列表
     *
     * @return
     */
    @GetMapping(value = "/listKuaiJiZhunZe")
    public ApiResponse<List<DataDictionaryVo>> listKuaiJiZhunZe() {
        return ApiResponse.success(dataDictionaryService.listKuaiJiZhunZe());
    }
}
