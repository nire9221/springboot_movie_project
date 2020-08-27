package com.team2.movie.dao.dto;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("ticket")
public class Ticket {

	private int groupNum;
	private String kakaoId;
	private int seatid;
	private int ticketPrice;
	private int ticketDiscount;

    public Ticket() {
    }

    public Ticket(int groupNum, String kakaoId, int seatid, int ticketPrice, int ticketDiscount) {
        this.groupNum = groupNum;
        this.kakaoId = kakaoId;
        this.seatid = seatid;
        this.ticketPrice = ticketPrice;
        this.ticketDiscount = ticketDiscount;
    }
}