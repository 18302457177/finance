package com.song.biz.dto.vo;


import lombok.Data;

import java.util.Date;

@Data
public class GetAssistCalculateEmployeeVo extends GetAssistCalculateVo {

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职务
     */
    private String position;

    /**
     * 岗位
     */
    private String job;

    /**
     * 手机
     */
    private String phone;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 入职日期
     */
    private Date startDate;

    /**
     * 离职日期
     */
    private Date departureDate;
}