package com.song.biz.dto.form;


import lombok.Data;

import java.util.Date;

/**
 * 辅助核算（存货）
 */
@Data
public class CreateAssistCalculateInventoryForm extends CreateAssistCalculateForm {

    /**
     * 规格型号
     */
    private String specifications;

    /**
     * 存货类别
     */
    private String inventoryCate;

    /**
     * 计量单位
     */
    private String units;

    /**
     * 启用日期
     */
    private Date startDate;

    /**
     * 停用日期
     */
    private Date endDate;
}