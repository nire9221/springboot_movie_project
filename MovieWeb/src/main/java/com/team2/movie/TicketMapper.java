package com.team2.movie;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team2.movie.dao.dto.TicketDto;

//@Mapper
public interface TicketMapper {
	
	public List<TicketDto> selectAllTickets();
	

}
