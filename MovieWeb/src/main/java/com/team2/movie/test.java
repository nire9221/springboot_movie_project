package com.team2.movie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		Date birth = new Date(0,0,02);
		SimpleDateFormat day = new SimpleDateFormat("yyyy년MM월dd일");
		System.out.println(day.format(birth));
	}

}
