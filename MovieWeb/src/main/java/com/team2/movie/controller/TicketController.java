package com.team2.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.movie.dao.dto.Ticket;
import com.team2.movie.mapper.TicketMapper;
import com.team2.movie.services.TicketService;

public class TicketController {
	
	@Autowired
	private TicketMapper mapper;
	
	@RequestMapping("listTicket")
	public String listTicket(Model model) {
		
		model.addAttribute("list", mapper.searchTicketAll());
		return "listTicket";
		
		List<Ticket> result = TicketService.searchTicketAll();
		
	}

}
