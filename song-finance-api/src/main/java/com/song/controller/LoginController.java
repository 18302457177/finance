package com.song.controller;


import com.song.biz.dto.form.*;
import com.song.biz.service.MemberLoginService;
import com.song.common.dto.ApiResponse;
import com.song.common.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录模块
 */
@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "用户登录模块")
public class LoginController {
    final MemberLoginService memberLoginService;
    final PasswordEncoder passwordEncoder;
    @Operation(summary = "密码加密")
    @GetMapping(value = "/password/{password}")
    public String getClientId(@PathVariable("password")String password) {

        return passwordEncoder.encode(password);
    }

    @Operation(summary = "密码校验")
    @GetMapping(value = "/match/{password}")
    public boolean match(@PathVariable("password")String password) {
        //$10$HD.4cEFCzEwL9/4R1dtVyevlbEjK5CT5tumKiYcdp94QfqZzbEe.a
        //$10$HD.4cEFCzEwL9/4R1dtVyevlbEjK5CT5tumKiYcdp94QfqZzbEe.a
        return passwordEncoder.matches(password,"$2a$10$HD.4cEFCzEwL9/4R1dtVyevlbEjK5CT5tumKiYcdp94QfqZzbEe.a");
    }


    /**
     * 获取客户端Id
     *
     * @return
     */
    @Operation(summary = "获取客户端Id")
    @GetMapping(value = "/getClientId")
    public ApiResponse<String> getClientId() {
        String result = memberLoginService.getClientId();
        return ApiResponse.success(result);
    }

    /**
     * 获取图形验证码
     *
     * @param form
     * @return
     */
    @Operation(summary = "获取图形验证码")
    @PostMapping(value = "/getBase64Code")
    public ApiResponse<String> getBase64Code(@RequestBody GetBase64CodeForm form) {
        String code = memberLoginService.getBase64Code(form);
        return ApiResponse.success(code);
    }

    /**
     * 获取短信验证码
     *
     * @param form
     * @return
     */
    @Operation(summary = "获取短信验证码")
    @GetMapping(value = "/sendSmsCode")
    public ApiResponse<Void> sendSmsCode(@Validated @ModelAttribute GetSmsCodeForm form) {
        memberLoginService.sendSmsCode(form);
        return ApiResponse.success();
    }

    /**
     * 手机密码登录
     *
     * @param form
     * @return
     */
    @Operation(summary = "手机密码登录")
    @PostMapping(value = "/phonePasswordLogin")
    public ApiResponse<TokenResponse> phonePasswordLogin(@Validated @RequestBody PhonePasswordLoginForm form) {
        TokenResponse tokenResponse = memberLoginService.phonePasswordLogin(form);
        return ApiResponse.success(tokenResponse);
    }

    /**
     * 手机短信登录
     *
     * @param request
     * @return
     */
    @Operation(summary = "手机短信登录")
    @PostMapping(value = "/phoneSmsCodeLogin")
    public ApiResponse<TokenResponse> phoneSmsCodeLogin(@Validated @RequestBody PhoneSmsCodeLoginForm request) {
        TokenResponse tokenResponse = memberLoginService.phoneSmsCodeLogin(request);
        return ApiResponse.success(tokenResponse);
    }

    /**
     * 获取客户端token
     *
     * @param request
     * @return
     */
    @Operation(summary = "获取客户端token")
    @GetMapping(value = "/getClientToken")
    public ApiResponse<TokenResponse> getClientToken(@Validated @ModelAttribute GetClientTokenForm request) {
        TokenResponse result = memberLoginService.getClientToken(request.getClientId());
        return ApiResponse.success(result);
    }
}
