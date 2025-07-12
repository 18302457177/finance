package com.song.biz.service.impl;


import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.VoucherSubjectDetail;
import com.song.biz.dto.AdminDTO;
import com.song.biz.mapper.VoucherSubjectDetailMapper;
import com.song.biz.service.VoucherSubjectDetailService;
import com.song.common.exception.BizException;
import com.song.common.service.TokenService;
import com.song.common.util.DateUtil;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.song.biz.domain.VoucherSubjectDetailField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherSubjectDetailServiceImpl implements VoucherSubjectDetailService {
    final VoucherSubjectDetailMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;

    /**
     * 根据凭证id列表查询凭证科目明细
     *
     * @param voucherIds
     * @return
     */
    @Override
    public List<VoucherSubjectDetail> listByVoucherIds(Set<Long> voucherIds) {
        MyBatisWrapper<VoucherSubjectDetail> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, VoucherId, SubjectId, CurrencyConfigId, ExchangeRate, OriginalCurrency, SubjectNum,
                SubjectUnitPrice, Balance, DebitAmount, CreditAmount, Summary, EnableAssistCalculateConfigs, EnableForeignCurrencyConfig,
                EnableNumberCalculateConfig);
        wrapper.whereBuilder().andIn(VoucherId, voucherIds)
                .andEq(DelFlag, false);
        return mapper.list(wrapper);
    }

    /**
     * 根据凭证id查询凭证科目明细
     *
     * @param voucherId
     * @return
     */
    @Override
    public List<VoucherSubjectDetail> listByVoucherId(Long voucherId) {
        MyBatisWrapper<VoucherSubjectDetail> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, VoucherId, SubjectId, CurrencyConfigId, Summary, OriginalCurrency,
                ExchangeRate, OriginalCurrency, SubjectNum, SubjectUnitPrice, Balance,
                DebitAmount, CreditAmount);
        wrapper.whereBuilder()
                .andEq(VoucherId, voucherId)
                .andEq(DelFlag, false);
        return mapper.list(wrapper);
    }

    /**
     * 删除凭证科目明细
     *
     * @param voucherId
     * @return
     */
    @Override
    public boolean del(long voucherId) {
        MyBatisWrapper<VoucherSubjectDetail> wrapper = new MyBatisWrapper<>();
        wrapper.update(DelFlag, true)
                .update(MemberId, tokenService.getThreadLocalUserId())
                .update(UpdateTime, DateUtil.getSystemTime())
                .whereBuilder()
                .andEq(VoucherId, voucherId)
                .andEq(DelFlag, false);
        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("删除凭证科目明细失败");
        }
        return true;
    }
}
