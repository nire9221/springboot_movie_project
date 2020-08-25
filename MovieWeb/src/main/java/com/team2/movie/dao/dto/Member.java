package com.team2.movie.dao.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String kakaoId;
	private String name;
	private Date birth;
	private String phone;
	private String email;
}
