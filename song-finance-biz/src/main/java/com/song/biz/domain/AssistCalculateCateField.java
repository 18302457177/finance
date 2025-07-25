package com.song.biz.domain;


import com.song.mybatis.help.DbField;
import com.song.mybatis.help.FieldResult;

import java.util.Collections;

public class AssistCalculateCateField {
    public static DbField Id = new DbField("id","id","BIGINT","java.lang.Long");

    public static DbField Name = new DbField("name","name","VARCHAR","java.lang.String");

    public static DbField CustomerColumn = new DbField("customer_column","customerColumn","VARCHAR","java.lang.String");

    public static DbField TenantId = new DbField("tenant_id","tenantId","BIGINT","java.lang.Long");

    public static DbField Disable = new DbField("disable","disable","BIT","java.lang.Boolean");

    public static DbField CreateTime = new DbField("create_time","createTime","TIMESTAMP","java.util.Date");

    public static DbField UpdateTime = new DbField("update_time","updateTime","TIMESTAMP","java.util.Date");

    public static DbField MemberId = new DbField("member_id","memberId","BIGINT","java.lang.Long");

    public static DbField UpdateMemberId = new DbField("update_member_id","updateMemberId","BIGINT","java.lang.Long");

    public static DbField DelFlag = new DbField("del_flag","delFlag","BIT","java.lang.Boolean");

    public static DbField Level = new DbField("level","level","INTEGER","java.lang.Integer");

    public static DbField Code = new DbField("code","code","VARCHAR","java.lang.String");

    public static DbField Sort = new DbField("sort","sort","INTEGER","java.lang.Integer");

    public static DbField UseCount = new DbField("use_count","useCount","INTEGER","java.lang.Integer");

    public static FieldResult setId(Long id) {
        return new FieldResult(Id, Collections.singletonList(id));
    }

    public static FieldResult setName(String name) {
        return new FieldResult(Name, Collections.singletonList(name));
    }

    public static FieldResult setCustomerColumn(String customerColumn) {
        return new FieldResult(CustomerColumn, Collections.singletonList(customerColumn));
    }

    public static FieldResult setTenantId(Long tenantId) {
        return new FieldResult(TenantId, Collections.singletonList(tenantId));
    }

    public static FieldResult setDisable(Boolean disable) {
        return new FieldResult(Disable, Collections.singletonList(disable));
    }

    public static FieldResult setCreateTime(java.util.Date createTime) {
        return new FieldResult(CreateTime, Collections.singletonList(createTime));
    }

    public static FieldResult setUpdateTime(java.util.Date updateTime) {
        return new FieldResult(UpdateTime, Collections.singletonList(updateTime));
    }

    public static FieldResult setMemberId(Long memberId) {
        return new FieldResult(MemberId, Collections.singletonList(memberId));
    }

    public static FieldResult setUpdateMemberId(Long updateMemberId) {
        return new FieldResult(UpdateMemberId, Collections.singletonList(updateMemberId));
    }

    public static FieldResult setDelFlag(Boolean delFlag) {
        return new FieldResult(DelFlag, Collections.singletonList(delFlag));
    }

    public static FieldResult setLevel(Integer level) {
        return new FieldResult(Level, Collections.singletonList(level));
    }

    public static FieldResult setCode(String code) {
        return new FieldResult(Code, Collections.singletonList(code));
    }

    public static FieldResult setSort(Integer sort) {
        return new FieldResult(Sort, Collections.singletonList(sort));
    }

    public static FieldResult setUseCount(Integer useCount) {
        return new FieldResult(UseCount, Collections.singletonList(useCount));
    }
}