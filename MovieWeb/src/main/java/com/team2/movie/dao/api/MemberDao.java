package com.team2.movie.dao.api;

import com.team2.movie.dao.dto.Member;

public interface MemberDao {
	
	public void signUp(Member member);
	public Member login(String kakaoKey);
}
