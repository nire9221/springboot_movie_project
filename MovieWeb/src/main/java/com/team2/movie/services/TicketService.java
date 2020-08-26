package com.team2.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.team2.movie.dao.dto.Ticket;
import com.team2.movie.mapper.TicketMapper;

public class TicketService {

	@Autowired
	private TicketMapper mapper;

	public List<Ticket> searchTicketAll() {
		

		return mapper.searchTicketAll();
	}
}