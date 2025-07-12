package com.song.biz.dto.vo;


import lombok.Data;

import java.util.Date;

@Data
public class GetAssistCalculateProjectVo extends GetAssistCalculateVo {
    /**
     * 负责部门
     */
    private String responsibleDepartment;

    /**
     * 责任人
     */
    private String responsiblePerson;

    /**
     * 手机
     */
    private String phone;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;
}