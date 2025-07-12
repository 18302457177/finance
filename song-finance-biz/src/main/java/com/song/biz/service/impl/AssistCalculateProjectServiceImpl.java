package com.song.biz.service.impl;

import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.AssistCalculateProject;
import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.CreateAssistCalculateProjectForm;
import com.song.biz.dto.form.UpdateAssistCalculateProjectForm;
import com.song.biz.dto.vo.GetAssistCalculateProjectVo;
import com.song.biz.dto.vo.ListAssistCalculateProjectVo;
import com.song.biz.enums.AssistCalculateCateCodeEnum;
import com.song.biz.mapper.AssistCalculateProjectMapper;
import com.song.biz.service.AssistCalculateHandleService;
import com.song.common.exception.BizException;
import com.song.common.service.TokenService;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.song.biz.domain.AssistCalculateProjectField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateProjectServiceImpl implements AssistCalculateHandleService {
    final AssistCalculateProjectMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;

    @Override
    public AssistCalculateCateCodeEnum getAssistCalculateCateCodeEnum() {
        return AssistCalculateCateCodeEnum.PROJECT;
    }

    /**
     * 添加项目辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Object obj) {
        CreateAssistCalculateProjectForm form = (CreateAssistCalculateProjectForm) obj;
        AssistCalculateProject assistCalculateProject = objectConvertor.toAssistCalculateProject(form);
        assistCalculateProject.initDefault();
        assistCalculateProject.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateProject.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateProject.setTenantId(tokenService.getThreadLocalTenantId());
        return mapper.insert(assistCalculateProject) > 0;
    }

    /**
     * 查询项目辅助核算项目列表
     *
     * @return
     */
    @Override
    public List<ListAssistCalculateProjectVo> listByAssistCalculateSummaryIds(List<Long> assistCalculateSummaryIds) {
        MyBatisWrapper<AssistCalculateProject> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, ResponsibleDepartment, ResponsiblePerson, Phone, StartDate, EndDate, Disable, AssistCalculateSummaryId)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(AssistCalculateSummaryId, assistCalculateSummaryIds);
        List<AssistCalculateProject> assistCalculates = mapper.list(wrapper);
        return objectConvertor.toListAssistCalculateProjectVo(assistCalculates);
    }

    /**
     * 修改项目辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Object obj) {
        UpdateAssistCalculateProjectForm form = (UpdateAssistCalculateProjectForm) obj;
        MyBatisWrapper<AssistCalculateProject> wrapper = new MyBatisWrapper<>();
        wrapper.update(UpdateTime, new Date())
                .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                .update(ResponsibleDepartment, form.getResponsibleDepartment())
                .update(ResponsiblePerson, form.getResponsiblePerson())
                .update(StartDate, form.getStartDate())
                .update(EndDate, form.getEndDate())
                .update(Phone, form.getPhone())
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
     * 获取项目辅助核算详情
     *
     * @param id
     * @return
     */
    @Override
    public GetAssistCalculateProjectVo get(long id) {
        AssistCalculateProject assistCalculateProject = getById(id);
        GetAssistCalculateProjectVo result = objectConvertor.toGetAssistCalculateProjectVo(assistCalculateProject);
        return result;
    }

    /**
     * 查询项目辅助核算明细
     *
     * @param id
     * @return
     */
    private AssistCalculateProject getById(long id) {
        MyBatisWrapper<AssistCalculateProject> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, ResponsibleDepartment, ResponsiblePerson, Phone, StartDate, EndDate)
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, id)
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        return mapper.get(wrapper);
    }
}
