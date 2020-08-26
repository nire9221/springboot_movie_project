package com.team2.movie.dao.dto;

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
@Getter
@Setter
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
	private Long id;
	
	@Column(name = "MOVIECODE") //JOIN 
	private String movieCd;
	
	@Column(name = "MOVIENAME")
	private String movieNm;
	
	@Column(name = "MOVIE_ENG")
	private String movieNmEn;
	
	@Column(name = "PRDYEAR")
	private String prdtYear;
	
	@Column(name = "RELEASEDATE")
	private String openDt;
	
	@Column(name = "STATUS")
	private String prdtStatNm;
	
	@Column(name = "PRDCOUNTRY")
	private String nationAlt;
	
	@Column(name = "GENRE")
	private String genreAlt;
	
	@Column(name = "REPCOUNTRY")
	private String repNationNm;
	
	@Column(name = "DIRECTOR")  //list
	private String directors;
	
	@Column(name = "COMPANY") //list?
	private String companys;
	
	
	
	
	

	
	
	
}