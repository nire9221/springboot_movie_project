package com.team2.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.movie.services.KakaoPay;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class HomeController {
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping(value = "/home")
	public String mainPage() {
		return "home";
	}

	@RequestMapping(value = "/oauth", produces = "application/json")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
		KakaoController kr = new KakaoController();
		// 결과값을 node에 담아줌
		JsonNode node = kr.getAccessToken(code);
		// 결과값 출력
		System.out.println("node : "+node);
		// 노드 안에 있는 access_token값을 꺼내 문자열로 변환
		String token = node.get("access_token").toString();
		// 세션에 담아준다.
		System.out.println("token값 : "+token);
		session.setAttribute("token", token);

		return "logininfo";
	}

	@RequestMapping(value = "/logout", produces = "application/json")
	public String Logout(HttpSession session) {
		// kakao restapi 객체 선언
		KakaoController kr = new KakaoController();
		// 노드에 로그아웃한 결과값음 담아줌 매개변수는 세션에 잇는 token을 가져와 문자열로 변환
		JsonNode node = kr.Logout(session.getAttribute("token").toString());
		// 결과 값 출력
		System.out.println("node : "+node);
		System.out.println("로그인 후 반환되는 아이디 : " + node.get("id"));
		
		session.setAttribute("id", node.get("id"));
		return "signup";
		//return "redirect:/signup";
	}
	
//////////////////////////////////////////////////////////	
	
	@Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;
    
	
	@GetMapping("/testpay")
	public String test() {
		return "kakaopay";
	}
    
//    @GetMapping("/kakaoPay")
//    public void kakaoPayGet() {
//        
//    }
    
    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");
        System.out.println("여기로 오겠지");
        return "redirect:" + kakaopay.kakaoPayReady();
        //return "kakaopay.kakaoPayReady()";
    }
    
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
    	System.out.println("여기 오냐?");
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        
        return "ordercheck";
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/seat")
    public String seat() {
    	return "seatselect";
    }
    
    @GetMapping("/seattest")
    public String seattest(@RequestParam("seat") String seat, Model model) {
    	model.addAttribute("seat", seat);
    	return "seattest";
    }
    
    @GetMapping("/seattest2")
    public String seattest2(@RequestParam("seat") String seat) {
    	System.out.println(seat);
    	return "seattest2";
    }
}
