package com.song.controller;


import com.song.biz.dto.form.GenerateMpRegCodeForm;
import com.song.biz.dto.form.PhoneRegisterForm;
import com.song.biz.dto.vo.GenerateMpRegCodeVo;
import com.song.biz.service.MemberRegService;
import com.song.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 注册模块
 */
@RestController
@RequestMapping(value = "/reg")
@RequiredArgsConstructor
@Slf4j
public class RegController {
    final MemberRegService memberRegService;

    /**
     * 手机号注册
     *
     * @param form
     * @return
     */
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
    @GetMapping(value = "/generateMpRegCode")
    public ApiResponse<GenerateMpRegCodeVo> generateMpRegCode(@Validated @ModelAttribute GenerateMpRegCodeForm request) {
        GenerateMpRegCodeVo result = memberRegService.generateMpRegCode(request.getClientId());
        return ApiResponse.success(result);
    }
}
