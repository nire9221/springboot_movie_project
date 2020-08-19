package com.team2.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
@Controller
public class HomeController {
	@GetMapping("/")
	public String hi() {
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
		return "redirect:/signup";
	}
}
