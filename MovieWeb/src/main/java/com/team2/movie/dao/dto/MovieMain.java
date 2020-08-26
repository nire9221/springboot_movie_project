package com.team2.movie.dao.dto;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Builder
@NoArgsConstructor // 기본생성자 자동추가
@Table(name = "movie")
@Entity // table 과 링크될 클래스
public class MovieMain {
    	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성규칙 - key값은 자동으로 증가
	@Column(name = "MOVIEID")
	@NonNull
	private Long movieId;
	
	@Column(name = "RNUM")
	private String rnum;
	
	@Column(name = "RANK")
	private String rank;
	
	@Column(name = "RANKINTEN")
	private String rankInten;
	
	@Column(name = "RANK_OLD_NEW")
	private String rankOldAndNew;
	
	@Column(name = "MOVIECODE")   //join....
	private String movieCd;
	
	@Column(name = "TITLE")
	private String movieNm;

	@Column(name = "RELASEDATE")
	private String openDt;
	
	@Column(name = "SALESAMT")
	private String salesAmt;
	
	@Column(name = "SALESSHARE")
	private String salesShare;
	
	@Column(name = "SALESINTEN")
	private String salesInten;
	
	@Column(name = "SALESCHANGE")
	private String salesChange;
	
	@Column(name = "SALESACC")
	private String salesAcc;
	
	@Column(name = "SALESCNT")
	private String audiCnt;
	
	@Column(name = "AUDIINTEN")
	private String audiInten;
	
	@Column(name = "AUDICHANGE")
	private String audiChange;
	
	@Column(name = "AUDIACC")
	private String audiAcc;
	
	@Column(name = "SCRNCNT")
	private String scrnCnt;
	
	@Column(name = "SHOWCNT")
	private String showCnt;
	
	
}