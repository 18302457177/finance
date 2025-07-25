package com.song.biz.service.impl;


import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.AssistCalculateInventory;
import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.CreateAssistCalculateInventoryForm;
import com.song.biz.dto.form.UpdateAssistCalculateInventoryForm;
import com.song.biz.dto.vo.GetAssistCalculateInventoryVo;
import com.song.biz.dto.vo.ListAssistCalculateInventoryVo;
import com.song.biz.enums.AssistCalculateCateCodeEnum;
import com.song.biz.mapper.AssistCalculateInventoryMapper;
import com.song.biz.service.AssistCalculateCateService;
import com.song.biz.service.AssistCalculateHandleService;
import com.song.common.exception.BizException;
import com.song.common.service.TokenService;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

import static com.song.biz.domain.AssistCalculateInventoryField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateInventoryServiceImpl implements AssistCalculateHandleService {
    final AssistCalculateInventoryMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final TransactionTemplate transactionTemplate;
    final AssistCalculateCateService assistCalculateCateService;

    @Override
    public AssistCalculateCateCodeEnum getAssistCalculateCateCodeEnum() {
        return AssistCalculateCateCodeEnum.INVENTORY;
    }

    /**
     * 添加存货辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Object obj) {
        CreateAssistCalculateInventoryForm form = (CreateAssistCalculateInventoryForm) obj;
        AssistCalculateInventory assistCalculateInventory = objectConvertor.toAssistCalculateInventory(form);
        assistCalculateInventory.initDefault();
        assistCalculateInventory.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateInventory.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateInventory.setTenantId(tokenService.getThreadLocalTenantId());
        return mapper.insert(assistCalculateInventory) > 0;
    }

    /**
     * 查询存货辅助核算存货列表
     *
     * @return
     */
    @Override
    public List<ListAssistCalculateInventoryVo> listByAssistCalculateSummaryIds(List<Long> assistCalculateSummaryIds) {
        MyBatisWrapper<AssistCalculateInventory> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Specifications, InventoryCate, Units, StartDate, EndDate, Disable, AssistCalculateSummaryId)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(AssistCalculateSummaryId, assistCalculateSummaryIds);

        List<AssistCalculateInventory> assistCalculates = mapper.list(wrapper);
        return objectConvertor.toListAssistCalculateInventoryVo(assistCalculates);
    }

    /**
     * 修改存货辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Object obj) {
        UpdateAssistCalculateInventoryForm form = (UpdateAssistCalculateInventoryForm) obj;
        MyBatisWrapper<AssistCalculateInventory> wrapper = new MyBatisWrapper<>();
        wrapper.update(UpdateTime, new Date())
                .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                .update(Specifications, form.getSpecifications())
                .update(InventoryCate, form.getInventoryCate())
                .update(Units, form.getUnits())
                .update(StartDate, form.getStartDate())
                .update(EndDate, form.getEndDate())
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, form.getId())
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);

        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("修改失败");
        }
        return true;
    }

    /**
     * 获取存货辅助核算详情
     *
     * @param id
     * @return
     */
    @Override
    public GetAssistCalculateInventoryVo get(long id) {
        AssistCalculateInventory assistCalculateInventory = getById(id);
        GetAssistCalculateInventoryVo result = objectConvertor.toGetAssistCalculateInventoryVo(assistCalculateInventory);
        return result;
    }

    /**
     * 查询存货辅助核算明细
     *
     * @param id
     * @return
     */
    private AssistCalculateInventory getById(long id) {
        MyBatisWrapper<AssistCalculateInventory> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Specifications, InventoryCate, Units, StartDate, EndDate)
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, id)
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        return mapper.get(wrapper);
    }
}
