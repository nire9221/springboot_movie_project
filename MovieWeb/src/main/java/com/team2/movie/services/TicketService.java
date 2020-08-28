package com.team2.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.movie.dao.dto.Ticket;
//import com.team2.movie.dao.dto.TicketDto;

@Service("TicketService")
@Transactional
public class TicketService {
//    @Autowired
//    TicketMapper ticketMapper;
//
//    public List<TicketDto> selectAllTickets() {
//        return ticketMapper.selectAllTickets();
//    }
//
//    public Ticket selectTicketByTicketId(int TicketId) {
//        return ticketMapper.selectTicketByTicketId(TicketId);
//    }
//
//    public void newTicket(Ticket ticket) {
//        ticketMapper.insertTicket(ticket);
//    }
}