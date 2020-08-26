package com.team2.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.team2.movie.dao.api.MovieDetailDao;
import com.team2.movie.dao.api.MovieMainDao;
import com.team2.movie.dao.dto.MovieDetail;
import com.team2.movie.dao.dto.MovieMain;

@Service
@Configurable
public class RepoService {

	@Autowired
	private MovieMainDao movieMainDao;

	@Autowired
	private MovieDetailDao movieDetailDao; 
	
	public RepoService() {

	}

	// ---------------movieMain----------------
	public MovieMain saveMovie(MovieMain movie) {
		System.out.println("dao " + movie);
		movieMainDao.save(movie);
		return movie;
	}

	public List<MovieMain> getMovieMainAll() {
		List<MovieMain> movieMain = movieMainDao.findAll();
		return movieMain;
	}

	public Optional<MovieMain> findMovieById(Long id) {
		Optional<MovieMain> movie = movieMainDao.findById(id);
		return movie;
	}

	public void deleteMovie(Long id) {
		movieMainDao.deleteById(id);
	}	
	
	// -------------movieDetail--------------

	public MovieDetail saveMovieDetail(MovieDetail movie) {
		movieDetailDao.save(movie);
		return movie;
	}

	public List<MovieDetail> getMovieDetailAll() {
		List<MovieDetail> movieDetail = movieDetailDao.findAll();
		return movieDetail;
	}
	
	public Optional<MovieDetail> findMovieDetailById(Long id) {
		Optional<MovieDetail> movie = movieDetailDao.findById(id);
		return movie;
	}

	public void deleteMovieDetail(Long id) {
		movieDetailDao.deleteById(id);
	}
}



//public void updateById(Long id, MovieMain movie) {
//	Optional<MovieMain> e = movieMainDao.findById(id);
//	if (e.isPresent()) {
//		e.get().setMbrNo(movie.getId);
//		e.get().setName(movie.getTitle());
//		movieMainDao.save(movie);
//	}
//}