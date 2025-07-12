package com.song.biz.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.biz.domain.AssistCalculateCate;
import com.song.biz.dto.form.CreateAssistCalculateCateForm;
import com.song.biz.dto.form.DelAssistCalculateCateForm;
import com.song.biz.dto.form.UpdateAssistCalculateCateForm;
import com.song.biz.dto.vo.GetAssistCalculateCateVo;
import com.song.biz.dto.vo.ListCalculateCateVo;

import java.util.List;
import java.util.Set;

public interface AssistCalculateCateService {
    /**
     * 添加辅助核算类别
     *
     * @param form
     * @return
     */
    boolean create(CreateAssistCalculateCateForm form) throws JsonProcessingException;

    /**
     * 删除辅助核算类别
     *
     * @param form
     * @return
     */
    boolean del(DelAssistCalculateCateForm form);

    /**
     * 修改辅助核算类别
     *
     * @param form
     * @return
     */
    boolean update(UpdateAssistCalculateCateForm form) throws JsonProcessingException;

    /**
     * 查询辅助核算类别信息
     *
     * @param id
     * @return
     */
    GetAssistCalculateCateVo getById(long id) throws JsonProcessingException;

    /**
     * 查询辅助核算列表
     *
     * @return
     */
    List<ListCalculateCateVo> list();

    /**
     * 查询辅助核算列表
     *
     * @return
     */
    List<AssistCalculateCate> list(Set<Long> ids);


    /**
     * 查询辅助核算类别信息
     *
     * @param id
     * @return
     */
    AssistCalculateCate getById2(long id);

    /**
     * 增加辅助核算类别使用计数
     *
     * @param id
     * @param count
     * @return
     */
    boolean addUseCount(long id, int count);

    /**
     * 减少辅助核算类别使用计数
     *
     * @param id
     * @param count
     * @return
     */
    boolean deductUseCount(long id, int count);

    /**
     * 增加辅助核算类别使用计数
     *
     * @param ids
     * @return
     */
    boolean addUseCount(Set<Long> ids);

    /**
     * 扣减辅助核算类别使用计数
     *
     * @param ids
     * @return
     */
    boolean deductUseCount(Set<Long> ids);
}
