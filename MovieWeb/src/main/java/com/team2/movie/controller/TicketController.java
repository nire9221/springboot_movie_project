package com.team2.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team2.movie.TicketMapper;
import com.team2.movie.dao.api.MovieDetailDao;
import com.team2.movie.dao.api.MovieMainDao;

public class TicketController {

	@Autowired
	private TicketMapper mapper;

//				
	@RequestMapping("selectAllTickets")
	public String selectAllTickets(Model model) {
//				
		model.addAttribute("list", mapper.selectAllTickets());
		return "listTicket";
	}
//				
}
