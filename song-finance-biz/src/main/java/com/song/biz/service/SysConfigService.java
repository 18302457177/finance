package com.song.biz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.biz.dto.SmsTemplateDTO;
import com.song.biz.dto.form.SaveSendSmsCodeTemplateConfigForm;


public interface SysConfigService {
    /**
     * 保存短信模板
     * @param form
     * @return
     * @throws JsonProcessingException
     */
    boolean saveSendSmsCodeTemplateConfig(SaveSendSmsCodeTemplateConfigForm form) throws JsonProcessingException;

    /**
     * 从缓存中获取短信模板
     *
     * @param configKey
     * @return
     */
    SmsTemplateDTO getSmsTemplateByCache(String configKey);
}