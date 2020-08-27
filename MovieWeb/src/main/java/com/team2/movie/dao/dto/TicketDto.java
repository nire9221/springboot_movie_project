package com.team2.movie.dao.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Data				
public class TicketDto {				
				
	private int groupNum;			
	private int seatid;			
	private int ticketId; // pk from ticket table			
	private String title; // from movie table			
	private Date date; // from venue table			
	private String start; // from venue table			
	private String end; // from venue table			
	private String venueNo; // from venue table			
	private String seatNo; // from seat table
	
}
//	private double ticketDiscount;			
//	private int ticketPrice;			
//}
//
//@Data
//@Table(name = "ticket")
//@Entity
//public class TicketDto {
//
//	@Id
////	@Column(name = "name")
////	@NonNull
////	private String name;   // member name from member table	
//
//	@Column(name = "groupNum")
//	private int groupNum;
//
//	@Column(name = "kakaoId")
//	private String kakaoId;
//
//	@Column(name = "seatid")
//	private int seatid;
//
//	@Column(name = "ticketPrice")
//	private int ticketPrice;
//
//	@Column(name = "ticketDiscount")
//	private int ticketDiscount;
//
//}