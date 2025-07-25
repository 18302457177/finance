package com.song.biz.service;


import com.song.biz.dto.form.*;
import com.song.biz.dto.vo.GetAccountBookVo;
import com.song.biz.dto.vo.ListAccountBookVo;
import com.song.mybatis.help.PageInfo;

public interface AccountBookService {
    /**
     * 查询账套明细
     *
     * @param id
     * @return
     */
    GetAccountBookVo get(long id);

    /**
     * 查询账套列表
     *
     * @param request
     * @return
     */
    PageInfo<ListAccountBookVo> list(ListAccountBookForm request);

    /**
     * 创建账套
     * @param request
     * @return
     */
    boolean add(AddAccountBookForm request);

    /**
     * 禁用启用账套
     *
     * @param form
=     * @return 结果
     */
    boolean disable(AccountBookDisableForm form);

    /**
     * 删除账套
     *
     * @param form 账套id
     * @return 结果
     */
    boolean del(DelForm form);

    /**
     * 编辑账套
     *
     * @param form
     * @return
     */
    boolean update(UpdateAccountBookForm form);
}
