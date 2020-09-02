package com.team2.movie.dao.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Title {
	
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column
	private String title;
	
	@Builder
	public Title(String title) {
		this.title = title;
	}
	
	

}
