package com.team2.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.movie.dao.api.TitleRepo;
import com.team2.movie.dao.dto.Title;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TitleController {

	private TitleRepo title;

	public TitleController(TitleRepo title) {
		this.title = title;
	}

	@RequestMapping("/bookingTitle")
	public String bookingTitle() {
		return "bookingTitle";
	}

}
