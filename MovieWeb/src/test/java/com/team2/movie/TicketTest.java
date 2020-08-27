//package com.team2.movie;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
////@Slf4j
////@SpringBootTest
////@RunWith(SpringRunner.class)
////@Transactional
////public class TicketTest {
////    @Autowired
////    TicketService service;
////
//////    @Test
//////    public void selectTicketByTicketId() {
//////        TicketDto ticketdto = service.selectTicketByTicketId(int ticketdto);
//////        		log.info("city : {}", city);
//////    }
////
////    @Test
////    public void selectAllTickets() {
////        List<TicketDto> cities = service.selectAllTickets();
////        log.info("tickets : {}", tickets);
////    }
////
////
//////    @Test
//////    public void addCities() {
//////        service.addCity(new City("뉴욕", "미국", 1_000_000L));
//////        service.addCity(new City("런던", "영국", 2_000_000L));
//////        service.addCity(new City("파리", "프랑스", 3_000_000L));
//////    }여기까지가 스프링부트 
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//
//public class TicketTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void selectAllTickets() throws Exception {
//        mockMvc.perform(get("/selectAllTickets"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("test successfully started"))
//                .andDo(print());
//    }
//}