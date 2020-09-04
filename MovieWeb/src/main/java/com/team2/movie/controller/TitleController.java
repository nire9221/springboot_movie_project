package com.team2.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team2.movie.dao.dto.Movie;
import com.team2.movie.dao.dto.MovieDto;
import com.team2.movie.services.MovieService;

@Controller
public class TitleController {
	
	private MovieService titleService;
	
	public TitleController(MovieService titleService) {
		this.titleService = titleService;
	}
	
	@GetMapping("/bookingpage")
	public String list(Model model) {
		@SuppressWarnings("unused")
		List<Movie> titleDtoList = titleService.findAll();
		model.addAttribute("titles", titleDtoList);
		return "bookingpage.html";
	}
	
	 @GetMapping("/seatselect")
   public String seat() {
   	return "seatselect";
   }
//	 @GetMapping("/seatselect")
//	    public String seat(@RequestParam("seat") String seat, Model model) {
//	       model.addAttribute("seat", seat);
//	       return "seatselect";
//	    }


		
}