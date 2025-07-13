package com.song.controller;


import com.song.biz.dto.form.GenerateMpRegCodeForm;
import com.song.biz.dto.form.PhoneRegisterForm;
import com.song.biz.dto.vo.GenerateMpRegCodeVo;
import com.song.biz.service.MemberRegService;
import com.song.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 注册模块
 */
@RestController
@RequestMapping(value = "/reg")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "注册模块")
public class RegController {
    final MemberRegService memberRegService;

    /**
     * 手机号注册
     *
     * @param form
     * @return
     */
    @Operation(summary = "手机号注册")
    @PostMapping(value = "/phoneReg")
    public ApiResponse<Long> phoneReg(@Validated @RequestBody PhoneRegisterForm form) {
        return ApiResponse.success(memberRegService.phoneReg(form));
    }

    /**
     * 生成微信公众号二维码（关注注册）
     *
     * @param request
     * @return
     */
    @Operation(summary = "生成微信公众号二维码（关注注册）")
    @GetMapping(value = "/generateMpRegCode")
    public ApiResponse<GenerateMpRegCodeVo> generateMpRegCode(@Validated @ModelAttribute GenerateMpRegCodeForm request) {
        GenerateMpRegCodeVo result = memberRegService.generateMpRegCode(request.getClientId());
        return ApiResponse.success(result);
    }
}
