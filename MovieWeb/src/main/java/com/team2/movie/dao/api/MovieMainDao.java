package com.team2.movie.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.movie.dao.dto.MovieMain;

@Repository
public interface MovieMainDao extends JpaRepository<MovieMain, Long> {
	List<MovieMain> findByMovieNm(String movieNm);
	List<MovieMain> findByMovieCd(String movieCd);
}
