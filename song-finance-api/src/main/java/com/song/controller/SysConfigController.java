package com.song.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.biz.dto.form.SaveSendSmsCodeTemplateConfigForm;
import com.song.biz.service.SysConfigService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置
 */
@RestController
@RequestMapping(value = "/sysConfig")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "系统配置")
public class SysConfigController {
    final SysConfigService sysConfigService;

    /**
     * 保存短信验证码模板配置
      * @param form
     * @return
     * @throws JsonProcessingException
     */
    @Operation(summary = "保存短信验证码模板配置")
    @PostMapping(value = "/saveSendSmsCodeTemplateConfig")
    public ApiResponse<Boolean> saveSendSmsCodeTemplateConfig(@Valid @RequestBody SaveSendSmsCodeTemplateConfigForm form) throws JsonProcessingException {
        return ApiResponse.success(sysConfigService.saveSendSmsCodeTemplateConfig(form));
    }
}
