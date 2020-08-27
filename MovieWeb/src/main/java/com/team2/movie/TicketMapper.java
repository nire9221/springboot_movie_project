package com.team2.movie;


import org.apache.ibatis.annotations.Mapper;

import com.team2.movie.dao.dto.TicketDto;

import java.util.List;

@Mapper
public interface TicketMapper {
    
    List<TicketDto> selectAllTicket();
    TicketDto selectTicketByTicketid();
    void newTicket(TicketDto ticketdto);

}