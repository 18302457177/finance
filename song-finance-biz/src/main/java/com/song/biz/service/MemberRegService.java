package com.song.biz.service;


import com.song.biz.dto.form.PhoneRegisterForm;
import com.song.biz.dto.vo.GenerateMpRegCodeVo;
import com.song.common.dto.TokenResponse;
import com.song.wx.dto.MpSubscribeEventRequest;

public interface MemberRegService {

    /**
     * 注册 保存到数据库
     *
     * @param form
     * @return
     */
    long phoneReg(PhoneRegisterForm form);

    /**
     * 生成微信公众号二维码 用于关注注册
     *
     * @return
     */
    GenerateMpRegCodeVo generateMpRegCode(String clientId);

    void handleMpSubscribeEventRequest(MpSubscribeEventRequest mpSubscribeEventRequest);

    TokenResponse registerByMpOpenId(String appId, String clientId, String openId);

    /**
     * 扫描注册
     *
     * @param appId
     * @param openId
     * @return
     */
    long scReg(String appId, String openId);
}
