package com.song.biz.domain;


import com.song.mybatis.help.DbField;
import com.song.mybatis.help.FieldResult;

import java.util.Collections;

public class SysResourceField {
    public static DbField Id = new DbField("id","id","INTEGER","java.lang.Integer");

    public static DbField Pid = new DbField("pid","pid","INTEGER","java.lang.Integer");

    public static DbField Name = new DbField("name","name","VARCHAR","java.lang.String");

    public static DbField Path = new DbField("path","path","VARCHAR","java.lang.String");

    public static DbField Sort = new DbField("sort","sort","INTEGER","java.lang.Integer");

    public static DbField CreateTime = new DbField("create_time","createTime","TIMESTAMP","java.util.Date");

    public static DbField UpdateTime = new DbField("update_time","updateTime","TIMESTAMP","java.util.Date");

    public static DbField MemberId = new DbField("member_id","memberId","BIGINT","java.lang.Long");

    public static DbField UpdateMemberId = new DbField("update_member_id","updateMemberId","BIGINT","java.lang.Long");

    public static DbField DelFlag = new DbField("del_flag","delFlag","BIT","java.lang.Boolean");

    public static DbField Disable = new DbField("disable","disable","BIT","java.lang.Boolean");

    public static DbField NodePath = new DbField("node_path","nodePath","INTEGER","java.lang.Integer");

    public static FieldResult setId(Integer id) {
        return new FieldResult(Id, Collections.singletonList(id));
    }

    public static FieldResult setPid(Integer pid) {
        return new FieldResult(Pid, Collections.singletonList(pid));
    }

    public static FieldResult setName(String name) {
        return new FieldResult(Name, Collections.singletonList(name));
    }

    public static FieldResult setPath(String path) {
        return new FieldResult(Path, Collections.singletonList(path));
    }

    public static FieldResult setSort(Integer sort) {
        return new FieldResult(Sort, Collections.singletonList(sort));
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

    public static FieldResult setDisable(Boolean disable) {
        return new FieldResult(Disable, Collections.singletonList(disable));
    }

    public static FieldResult setNodePath(Integer nodePath) {
        return new FieldResult(NodePath, Collections.singletonList(nodePath));
    }
}