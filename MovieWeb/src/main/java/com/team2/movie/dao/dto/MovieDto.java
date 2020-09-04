package com.team2.movie.dao.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovieDto {
	private Long movieid;
	private String title;
	
	public Movie toEntity() {
		Movie build = Movie.builder()
				.movieid(movieid)
				.title(title)
				.build();
		return build;
	}
	
	@Builder
	public MovieDto (Long movieid, String title) {
		this.movieid = movieid;
		this.title = title;
	}

}
