package com.song.biz.service.impl;


import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.Member;
import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.UpdateEmailAndNameForm;
import com.song.biz.dto.vo.ListMemberVo;
import com.song.biz.mapper.MemberMapper;
import com.song.biz.service.MemberService;
import com.song.common.constant.CommonConstant;
import com.song.common.service.TokenService;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.song.biz.domain.MemberField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final MemberMapper memberMapper;
    final TokenService<AdminDTO> tokenService;
    final ObjectConvertor objectConvertor;

    /**
     * 注册
     *
     * @param tenantId 租户id
     * @return 会员id
     */
    @Override
    public long reg(long tenantId) {
        Member member = new Member();
        member.setTenantId(tenantId);
        member.setSysRoleIds("[" + CommonConstant.ROLE_MEMBER + "]");
        member.initDefault();
        memberMapper.insert(member);
        return member.getId();
    }

    /**
     * 获取会员信息
     *
     * @param id
     * @return
     */
    @Override
    public Member get(long id) {
        MyBatisWrapper<Member> myBatisWrapper = new MyBatisWrapper<>();
        myBatisWrapper.select(NickName, Id, Disable, Name, AvatarUrl, SysRoleIds,
                TenantId, Email)
                .whereBuilder().andEq(setId(id));
        return memberMapper.topOne(myBatisWrapper);
    }

    /**
     * 修改邮箱和姓名
     *
     * @param form
     * @return
     */
    @Override
    public boolean updateEmailAndName(UpdateEmailAndNameForm form) {
        MyBatisWrapper<Member> wrapper = new MyBatisWrapper<>();
        wrapper.update(setEmail(form.getEmail()))
                .update(setName(form.getName()))
                .whereBuilder()
                .andEq(setId(tokenService.getThreadLocalUserId()))
                .andEq(setDisable(false));
        return memberMapper.updateField(wrapper) > 0;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @Override
    public List<ListMemberVo> listMember() {
        MyBatisWrapper<Member> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, NickName, Name)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(Disable, false);
        List<Member> members = memberMapper.list(wrapper);
        return objectConvertor.toListMemberVo(members);
    }
    /**
     * 根据id查询会员列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<Member> listByIds(Set<Long> ids) {
        MyBatisWrapper<Member> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, NickName, Name)
                .whereBuilder()
                .andIn(Id, ids);
        return memberMapper.list(wrapper);
    }
}
