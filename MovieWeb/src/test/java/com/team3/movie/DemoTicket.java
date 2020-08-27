package com.team3.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoTicket {

    public static void main(String[] args)  {
        SpringApplication application = new SpringApplication(DemoTicket.class);
        application.run(args);
    }
}
