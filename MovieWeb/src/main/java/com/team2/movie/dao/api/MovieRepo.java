package com.team2.movie.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team2.movie.dao.dto.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long>{
//	@Query(value = "select title from movie", nativeQuery=true)
//	List<Movie> findTitle();
	public List<Movie> findAll();
	
//	public long count();
}