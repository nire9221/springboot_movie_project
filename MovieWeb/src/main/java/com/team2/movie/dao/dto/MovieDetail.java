package com.team2.movie.dao.dto;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Builder
@NoArgsConstructor // 기본생성자 자동추가
@Table(name = "moviedetail")
@Entity // table 과 링크될 클래스
public class MovieDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIEDETAILID")
	@NonNull
	private Long movieDetailId;
	
	@Column(name = "MOVIECODE") // JOIN
	private String movieCd;

	@Column(name = "MOVIENAME")
	private String movieNm;

	@Column(name = "MOVIE_ENG")
	private String movieNmEn;

	@Column(name = "MOVIE_ORIGINAL")
	private String movieNmOg;

	@Column(name = "PRDYEAR")
	private String prdtYear;

	@Column(name = "RUNTIME")
	private String showTm;

	@Column(name = "RELEASEDATE")
	private String openDt;

	@Column(name = "STATUS")
	private String prdtStatNm;

	@Column(name = "MOVIETYPE")
	private String typeNm;

	
	private ArrayList<Map> nations;
	private ArrayList<Map> actors;
	private ArrayList<Map> companys;
	private ArrayList<Map> directors;
	private ArrayList<Map> audits;
}