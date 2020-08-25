package com.team2.movie.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.movie.dao.dto.Member;
import com.team2.movie.test.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	public MemberMapper mapper;
	
	public List<Member> selectMember(){
		return mapper.selectMember();
	}
	
}
