package com.song.controller;


import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.GetUserSmsCodeForm;
import com.song.biz.dto.form.UpdateEmailAndNameForm;
import com.song.biz.dto.form.UpdatePasswordForm;
import com.song.biz.dto.form.UpdatePhoneForm;
import com.song.biz.dto.vo.CurrentInfoVo;
import com.song.biz.dto.vo.ListMemberVo;
import com.song.biz.service.MemberBindPhoneService;
import com.song.biz.service.MemberComService;
import com.song.biz.service.MemberService;
import com.song.common.dto.ApiResponse;
import com.song.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户模块
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "用户模块")
public class UserController {
    final MemberComService memberComService;
    final MemberService memberService;
    final MemberBindPhoneService memberBindPhoneService;
    final TokenService<AdminDTO> tokenService;

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping(value = "/currentUser")
    public ApiResponse<CurrentInfoVo> currentUser() {
        return ApiResponse.success(memberComService.getCurrentInfo());
    }

    /**
     * 修改邮箱和姓名
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改邮箱和姓名")
    @PostMapping(value = "/updateEmailAndName")
    public ApiResponse<Boolean> updateEmailAndName(@Validated @RequestBody UpdateEmailAndNameForm form) {
        return ApiResponse.success(memberService.updateEmailAndName(form));
    }

    /**
     * 修改密码
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改密码")
    @PostMapping(value = "/updatePassword")
    public ApiResponse<Boolean> updatePassword(@Validated @RequestBody UpdatePasswordForm form) {
        return ApiResponse.success(memberBindPhoneService.updatePassword(form));
    }

    /**
     * 修改手机号
     *
     * @param form
     * @return
     */
    @Operation(summary = "修改手机号")
    @PostMapping(value = "/updatePhone")
    public ApiResponse<Boolean> updatePhone(@Validated @RequestBody UpdatePhoneForm form) {
        return ApiResponse.success(memberComService.updatePhone(form));
    }

    /**
     * 获取图形验证码
     *
     * @return
     */
    @Operation(summary = "获取图形验证码")
    @GetMapping(value = "/getBase64Code")
    public ApiResponse<String> getBase64Code() {
        String result = memberComService.getBase64Code();
        return ApiResponse.success(result);
    }

    /**
     * 获取短信验证码
     *
     * @param request
     * @return
     */
    @Operation(summary = "获取短信验证码")
    @GetMapping(value = "/sendSmsCode")
    public ApiResponse<Void> sendSmsCode(@Validated @ModelAttribute GetUserSmsCodeForm request) {
        memberComService.sendSmsCode(request);
        return ApiResponse.success();
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Operation(summary = "获取用户列表")
    @GetMapping(value = "/listMember")
    public ApiResponse<List<ListMemberVo>> listMember() {
        return ApiResponse.success(memberService.listMember());
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Operation(summary = "退出登录")
    @PostMapping(value = "/loginOut")
    public ApiResponse<Void> loginOut() {
        tokenService.clearToken();
        return ApiResponse.success();
    }
}
