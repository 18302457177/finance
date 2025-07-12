package com.song.biz.service.impl;


import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.AssistCalculateEmployee;
import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.CreateAssistCalculateEmployeeForm;
import com.song.biz.dto.form.UpdateAssistCalculateEmployeeForm;
import com.song.biz.dto.vo.GetAssistCalculateEmployeeVo;
import com.song.biz.dto.vo.ListAssistCalculateEmployeeVo;
import com.song.biz.enums.AssistCalculateCateCodeEnum;
import com.song.biz.mapper.AssistCalculateEmployeeMapper;
import com.song.biz.service.AssistCalculateHandleService;
import com.song.common.exception.BizException;
import com.song.common.service.TokenService;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.song.biz.domain.AssistCalculateEmployeeField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateEmployeeServiceImpl implements AssistCalculateHandleService {
    final AssistCalculateEmployeeMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;

    @Override
    public AssistCalculateCateCodeEnum getAssistCalculateCateCodeEnum() {
        return AssistCalculateCateCodeEnum.EMPLOYEE;
    }

    /**
     * 添加职员辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Object obj) {
        CreateAssistCalculateEmployeeForm form = (CreateAssistCalculateEmployeeForm) obj;
        AssistCalculateEmployee assistCalculateEmployee = objectConvertor.toAssistCalculateEmployee(form);
        assistCalculateEmployee.initDefault();
        assistCalculateEmployee.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateEmployee.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateEmployee.setTenantId(tokenService.getThreadLocalTenantId());

        return mapper.insert(assistCalculateEmployee) > 0;
    }

    /**
     * 查询客户辅助核算客户列表
     *
     * @return
     */
    @Override
    public List<ListAssistCalculateEmployeeVo> listByAssistCalculateSummaryIds(List<Long> assistCalculateSummaryIds) {
        MyBatisWrapper<AssistCalculateEmployee> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Sex, DepartmentCode, DepartmentName,
                Position, Job, Birthday, StartDate, DepartureDate, Phone, Disable,
                AssistCalculateSummaryId)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(AssistCalculateSummaryId, assistCalculateSummaryIds);
        List<AssistCalculateEmployee> assistCalculates = mapper.list(wrapper);
        return objectConvertor.toAssistCalculateEmployee(assistCalculates);
    }

    /**
     * 修改客户辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Object obj) {
        UpdateAssistCalculateEmployeeForm form = (UpdateAssistCalculateEmployeeForm) obj;
        MyBatisWrapper<AssistCalculateEmployee> wrapper = new MyBatisWrapper<>();
        wrapper.update(UpdateTime, new Date())
                .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                .update(Sex, form.getSex())
                .update(DepartmentCode, form.getDepartmentCode())
                .update(DepartmentName, form.getDepartmentName())
                .update(Position, form.getPosition())
                .update(Job, form.getJob())
                .update(Phone, form.getPhone())
                .update(Birthday, form.getBirthday())
                .update(StartDate, form.getStartDate())
                .update(DepartureDate, form.getDepartureDate())
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
     * 获取客户辅助核算详情
     *
     * @param id
     * @return
     */
    @Override
    public GetAssistCalculateEmployeeVo get(long id) {
        AssistCalculateEmployee assistCalculateEmployee = getById(id);
        GetAssistCalculateEmployeeVo result = objectConvertor.toGetAssistCalculateEmployeeVo(assistCalculateEmployee);
        return result;
    }

    /**
     * 查询职员辅助核算明细
     *
     * @param id
     * @return
     */
    private AssistCalculateEmployee getById(long id) {
        MyBatisWrapper<AssistCalculateEmployee> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Sex, DepartmentCode, DepartmentName, Position, Job, Phone, Birthday, StartDate, DepartureDate)
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, id)
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        return mapper.get(wrapper);
    }
}
