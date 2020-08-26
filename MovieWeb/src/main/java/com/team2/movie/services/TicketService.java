package com.team2.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.movie.dao.dto.Ticket;
import com.team2.movie.mapper.TicketMapper;


@Service
@Transactional
public class TicketService {
    @Autowired
    TicketMapper ticketMapper;
    

    public List<Ticket> searchAllTickets() {
        return ticketMapper.searchAllTickets();
    }

    public Ticket selectTicketbyTicketid(int ticketid) {
        return ticketMapper.selectTicketbyTicketId(ticketid);
    }
    
    public Ticket selectTicketbyUser(String kakaoid) {
        return ticketMapper.selectTicketbyUser(kakaoid);
    }


    public void newTicket(Ticket ticket) {
        ticketMapper.newTicket(ticket);
    }
}
