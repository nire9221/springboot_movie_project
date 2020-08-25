package com.team2.movie.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.team2.movie.dao.dto.Member;

@Repository
@Mapper
public interface MemberMapper {
	List<Member> selectMember();

}
