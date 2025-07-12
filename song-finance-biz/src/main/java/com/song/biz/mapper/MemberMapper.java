package com.song.biz.mapper;


import com.song.biz.domain.Member;
import com.song.mybatis.help.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends CommonMapper<Member> {
}