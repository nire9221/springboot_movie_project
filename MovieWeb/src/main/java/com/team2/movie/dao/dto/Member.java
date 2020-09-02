package com.team2.movie.dao.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Member")
@Entity
public class Member {
	@Id
	@Column(name = "kakaoId")
	@NonNull
	String kakaoId;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "birth")
	Date birth;
	
	@Column(name = "phone")
	String phone;
	
	@Column(name = "email")
	String email;
}
