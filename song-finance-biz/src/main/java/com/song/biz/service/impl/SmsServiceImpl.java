package com.song.biz.service.impl;


import com.song.biz.dto.SmsTemplateDTO;
import com.song.biz.enums.SmsCodeTypeEnum;
import com.song.biz.service.SmsService;
import com.song.biz.service.SysConfigService;
import com.song.common.exception.BizException;

import com.song.common.service.SmsCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    final SmsCommonService smsCommonService;
    final SysConfigService sysConfigService;

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param smsCode
     * @param smsCodeType
     */
    @Override
    public void sendSmsCode(String phone, String smsCode, String smsCodeType) {
        if (SmsCodeTypeEnum.REG.getCode().equals(smsCodeType)) {
            sendRegSmsCode(phone, smsCode);
        }
        if (SmsCodeTypeEnum.LOGIN.getCode().equals(smsCodeType)) {
            sendLoginSmsCode(phone, smsCode);
        }
        if (SmsCodeTypeEnum.UPDATE_PHONE.getCode().equals(smsCodeType)) {
            sendUpdatePhoneSmsCode(phone, smsCode);
        }
    }

    /**
     * 发送注册短信验证码
     *
     * @param phone
     * @param smsCode
     */
    @Override
    public void sendRegSmsCode(String phone, String smsCode) {
        SmsTemplateDTO smsTemplate = sysConfigService.getSmsTemplateByCache(SmsCodeTypeEnum.REG.getCode());
        if (smsTemplate == null) {
            throw new BizException("短信模板未配置或未加载到缓存中");
        }
        Map<String, String> templateParam = new HashMap<>();
        templateParam.put("code", smsCode);
        smsCommonService.sendSms(smsTemplate.getSignName(), smsTemplate.getTemplateCode(),
                phone, templateParam);
    }

    /**
     * 发送登录短信验证码
     *
     * @param phone
     * @param smsCode
     */
    @Override
    public void sendLoginSmsCode(String phone, String smsCode) {
        SmsTemplateDTO smsTemplate = sysConfigService.getSmsTemplateByCache(SmsCodeTypeEnum.LOGIN.getCode());
        if (smsTemplate == null) {
            throw new BizException("短信模板未配置或未加载到缓存中");
        }
        Map<String, String> templateParam = new HashMap<>();
        templateParam.put("code", smsCode);
        smsCommonService.sendSms(smsTemplate.getSignName(), smsTemplate.getTemplateCode(),
                phone, templateParam);
    }

    /**
     * 发送修改手机号短信验证码
     *
     * @param phone
     * @param smsCode
     */
    @Override
    public void sendUpdatePhoneSmsCode(String phone, String smsCode) {
        SmsTemplateDTO smsTemplate = sysConfigService.getSmsTemplateByCache(SmsCodeTypeEnum.UPDATE_PHONE.getCode());
        if (smsTemplate == null) {
            throw new BizException("短信模板未配置或未加载到缓存中");
        }
        Map<String, String> templateParam = new HashMap<>();
        templateParam.put("code", smsCode);
        smsCommonService.sendSms(smsTemplate.getSignName(), smsTemplate.getTemplateCode(),
                phone, templateParam);
    }
}
