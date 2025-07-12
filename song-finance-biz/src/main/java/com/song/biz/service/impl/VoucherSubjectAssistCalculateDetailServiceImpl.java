package com.song.biz.service.impl;


import com.song.biz.domain.VoucherSubjectAssistCalculateDetail;
import com.song.biz.dto.AdminDTO;
import com.song.biz.mapper.VoucherSubjectAssistCalculateDetailMapper;
import com.song.biz.service.VoucherSubjectAssistCalculateDetailService;
import com.song.common.service.TokenService;
import com.song.common.util.DateUtil;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.song.biz.domain.VoucherSubjectAssistCalculateDetailField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherSubjectAssistCalculateDetailServiceImpl implements VoucherSubjectAssistCalculateDetailService {
    final VoucherSubjectAssistCalculateDetailMapper mapper;
    final TokenService<AdminDTO> tokenService;

    /**
     * 根据凭证id列表查询凭证科目辅助核算明细列表
     *
     * @param voucherIds
     * @return
     */
    @Override
    public List<VoucherSubjectAssistCalculateDetail> listByVoucherIds(Set<Long> voucherIds) {
        MyBatisWrapper<VoucherSubjectAssistCalculateDetail> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, VoucherId, SubjectId, VoucherSubjectDetailId,
                AssistCalculateCateId, AssistCalculateId)
                .whereBuilder()
                .andIn(VoucherId, voucherIds)
                .andEq(DelFlag, false);
        return mapper.list(wrapper);
    }

    /**
     * 根据凭证id查询凭证科目辅助核算明细列表
     *
     * @param voucherId
     * @return
     */
    @Override
    public List<VoucherSubjectAssistCalculateDetail> listByVoucherId(Long voucherId) {
        MyBatisWrapper<VoucherSubjectAssistCalculateDetail> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, VoucherId, SubjectId, VoucherSubjectDetailId,
                AssistCalculateCateId, AssistCalculateId)
                .whereBuilder()
                .andEq(VoucherId, voucherId)
                .andEq(DelFlag, false);
        ;
        return mapper.list(wrapper);
    }

    /**
     * 删除凭证科目辅助核算明细
     *
     * @param voucherId
     * @return
     */
    @Override
    public boolean del(long voucherId) {
        MyBatisWrapper<VoucherSubjectAssistCalculateDetail> wrapper = new MyBatisWrapper<>();
        wrapper.update(DelFlag, true)
                .update(MemberId, tokenService.getThreadLocalUserId())
                .update(UpdateTime, DateUtil.getSystemTime())
                .whereBuilder()
                .andEq(VoucherId, voucherId)
                .andEq(DelFlag, false);
        return true;
    }
}
