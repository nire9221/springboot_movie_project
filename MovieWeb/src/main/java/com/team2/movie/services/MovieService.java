package com.team2.movie.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.movie.dao.api.MovieRepo;
import com.team2.movie.dao.dto.Movie;
import com.team2.movie.dao.dto.MovieDto;

@Service
public class MovieService {
	private MovieRepo movieRepo;
	
	public MovieService(MovieRepo movieRepo) {
		this.movieRepo = movieRepo;
	}
	
	public List<Movie> findAll() {
		return movieRepo.findAll();
	}
	
//	@Transactional
//	public List<Movie> findTitle () {
//		return titleRepo.findTitle();
//	}
}
