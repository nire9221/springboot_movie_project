package com.team2.movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team2.movie.mapper.TicketMapper;

@Controller
public class TicketController {

	@Autowired
	private TicketMapper mapper;
	
	@RequestMapping(value="/searchAllTickets", method=RequestMethod.POST)
	public String searchAllTickets(Model model, HttpServletRequest request) {
		
		model.addAttribute("list", mapper.searchAllTickets());
		
		return "TicketView"; //출력
		
//		@RequestMapping(value="searchTicketByTicketid", method=RequestMethod.POST)
//		public String searchTicketByTicketid(Model model, HttpServletRequest request) {
//			
//			model.addAttribute("list", mapper.searchAllTickets());
			
//			@RequestMapping(value="/searchAllTickets", method=RequestMethod.POST)
//			public String searchAllTickets(Model model, HttpServletRequest request) {
//				
//				model.addAttribute("list", mapper.searchAllTickets());
//				
//				return "ticketView"; //출력
	}
	
}