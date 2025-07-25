package com.song.biz.service;


import com.song.biz.domain.Member;
import com.song.biz.dto.form.GetBase64CodeForm;
import com.song.biz.dto.form.GetSmsCodeForm;
import com.song.biz.dto.form.PhonePasswordLoginForm;
import com.song.biz.dto.form.PhoneSmsCodeLoginForm;
import com.song.common.dto.TokenResponse;

public interface MemberLoginService {
    /**
     * 获取客户端id
     *
     * @return
     */
    String getClientId();

    /**
     * 获取图形验证码
     *
     * @param form
     * @return
     */
    String getBase64Code(GetBase64CodeForm form);

    /**
     * 获取短信验证码
     *
     * @param form
     * @return
     */
    void sendSmsCode(GetSmsCodeForm form);

    /**
     * 校验短信验证码
     *
     * @param phone
     * @param smsCode
     * @param smsCodeType
     * @return
     */
    boolean checkSmsCode(String phone, String smsCode, String smsCodeType);

    /**
     * 校验图形验证码
     *
     * @param clientId
     * @return
     */
    boolean checkBase64Code(String clientId, String code);

    /**
     * 手机账号密码登录
     *
     * @param form
     * @return
     */
    TokenResponse phonePasswordLogin(PhonePasswordLoginForm form);

    /**
     * 手机短信登录
     *
     * @param form
     * @return
     */
    TokenResponse phoneSmsCodeLogin(PhoneSmsCodeLoginForm form);

    /**
     * 获取客户端token
     *
     * @param clientId
     * @return
     */
    TokenResponse getClientToken(String clientId);

    TokenResponse loginSuccess(Member member, String clientId);
}
