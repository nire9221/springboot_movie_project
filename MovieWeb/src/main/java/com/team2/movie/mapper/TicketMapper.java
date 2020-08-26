package com.team2.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team2.movie.dao.dto.Ticket;

@Mapper
public interface TicketMapper {
	public List<Ticket> searchAllTickets();
	
	Ticket selectTicketbyTicketId(int ticketid); //티켓넘버로 조회
	
	Ticket  selectTicketbyUser(String kakaoid); //카카오 아이디로 조회
	
	void newTicket(Ticket ticket); //새 예매정보 생성
	
	
}
