//package com.team3.movie;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.team2.movie.services.TicketService;
//
//import lombok.extern.slf4j.Slf4j;
//
//
//
////@SpringBootApplication
////public class DemoTicket {
////
////	public static void main(String[] args) {
////		SpringApplication.run(DemoTicket.class, args);
////	}
////}
//
//
////import org.mybatis.spring.annotation.MapperScan;
////
////@SpringBootApplication
////@MapperScan(basePackages = "com.team3.movie")
////public class DemoTicket {
////}
//
//
//
//@Slf4j
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Transactional
//public class DemoTicket {
//    @Autowired
//    TicketService service;
//
//    @Test
//    public void getCityById() {
//        City city = service.getCityById(1L);
//        log.info("city : {}", city);
//    }
//
//    @Test
//    public void getAllCity() {
//        List<City> cities = service.getAllCity();
//        log.info("cities : {}", cities);
//    }
//
//
//    @Test
//    public void addCities() {
//        service.addCity(new City("뉴욕", "미국", 1_000_000L));
//        service.addCity(new City("런던", "영국", 2_000_000L));
//        service.addCity(new City("파리", "프랑스", 3_000_000L));
//    }
//
//}