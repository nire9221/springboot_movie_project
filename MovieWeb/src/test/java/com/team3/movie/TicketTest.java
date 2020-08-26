package com.team3.movie;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.team2.movie.dao.dto.Ticket;
import com.team2.movie.services.TicketService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TicketTest {
    @Autowired
    TicketService service;
    
    @Test
    public void searchAllTickets() {
        List<Ticket> ticket = service.searchAllTickets();
    }
    
//    @Test
//    public void selectTicketbyTicketid(int ticketid) {
//        Ticket ticket = service.selectTicketbyTicketid(ticketid);
//        log.info("ticketid : {}", ticketid);
//    }
//    
//    @Test
//    public void selectTicketbyUser(String kakaoid) {
//        Ticket ticket = service.selectTicketbyUser(kakaoid);
//        log.info("kakaoid : {}", kakaoid);
//    }


//    @Test
//    public void newTicket() {
//        service.newTicket(new Ticket('1, "123", '1', '500000', '1'));
//    }

}