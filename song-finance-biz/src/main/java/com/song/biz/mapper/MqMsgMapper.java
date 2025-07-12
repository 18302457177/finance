package com.song.biz.mapper;


import com.song.biz.domain.MqMsg;
import com.song.mybatis.help.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MqMsgMapper extends CommonMapper<MqMsg> {
}