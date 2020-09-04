package com.team2.movie.dao.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long movieid;
	
	@Column (name = "title")
	private String title;
	
	@Builder
	public Movie(Long movieid, String title) {
		this.movieid = movieid;
		this.title = title;
	}
	
}