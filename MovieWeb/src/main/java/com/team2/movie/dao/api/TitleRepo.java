package com.team2.movie.dao.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team2.movie.dao.dto.Title;

import java.util.List;

public interface TitleRepo extends JpaRepository<Title, Long> {


	public List<Title> findMovieByTitle() throws Exception;

}
