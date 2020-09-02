package com.team2.movie.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.team2.movie.dao.dto.MovieDetail;

@Repository
public interface MovieDetailDao extends JpaRepository <MovieDetail, Long>{
	List<MovieDetail> findByMovieCd(String movieCd);
}
