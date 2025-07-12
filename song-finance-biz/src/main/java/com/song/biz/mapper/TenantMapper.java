package com.song.biz.mapper;


import com.song.biz.domain.Tenant;
import com.song.mybatis.help.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantMapper extends CommonMapper<Tenant> {
}