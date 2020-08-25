package com.team2.movie.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.team2.movie.dao.api.MemberDao;
import com.team2.movie.dao.dto.Member;

public class MemberDaoImpl implements MemberDao {
	@Autowired
	MemberDao memberDao;
	
	@Override
	public void signUp(Member member) {
		memberDao.signUp(member);
	}

	@Override
	public Member login(String kakaoKey) {
		return memberDao.login(kakaoKey);
	}

}
