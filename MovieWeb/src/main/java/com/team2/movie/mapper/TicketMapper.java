package com.team2.movie.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team2.movie.dao.dto.Ticket;

@Mapper
public interface TicketMapper {
	public List<Ticket> searchTicketAll();



}
