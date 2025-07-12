package com.song.biz.service.impl;


import com.song.biz.domain.MemberBindWxOpenId;
import com.song.biz.mapper.MemberBindWxOpenIdMapper;
import com.song.biz.service.MemberBindWxOpenIdService;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.song.biz.domain.MemberBindWxOpenIdField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberBindWxOpenIdServiceImpl implements MemberBindWxOpenIdService {
    final MemberBindWxOpenIdMapper memberBindWxOpenIdMapper;

    /**
     * 判断appid和openid是否存在
     *
     * @param appid
     * @param openid
     * @return
     */
    @Override
    public MemberBindWxOpenId get(String appid, String openid) {
        MyBatisWrapper<MemberBindWxOpenId> myBatisWrapper = new MyBatisWrapper<>();
        myBatisWrapper.select(MemberId)
                .whereBuilder()
                .andEq(setAppId(appid)).andEq(setOpenId(openid));
        return memberBindWxOpenIdMapper.topOne(myBatisWrapper);
    }

    /**
     * 注册
     *
     * @param appid
     * @param openid
     * @param memberId
     * @return
     */
    @Override
    public boolean reg(String appid, String openid, long memberId) {
        MemberBindWxOpenId memberBindWxOpenId = new MemberBindWxOpenId();
        memberBindWxOpenId.setAppId(appid);
        memberBindWxOpenId.setOpenId(openid);
        memberBindWxOpenId.setMemberId(memberId);
        memberBindWxOpenId.initDefault();
        return memberBindWxOpenIdMapper.insertSelective(memberBindWxOpenId) > 0;
    }
}
