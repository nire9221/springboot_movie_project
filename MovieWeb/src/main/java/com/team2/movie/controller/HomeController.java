package com.team2.movie.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.movie.dao.dto.Member;
import com.team2.movie.test.service.MemberService;
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

	
	@RequestMapping(value="/signup",method= {RequestMethod.POST})
	public String signup(Member member, String kakaoKey, String userName, Date birth, String email,
						String phone) {
		return "main";
	}
	
	
	@Autowired
	MemberService memberService;
	@RequestMapping(value="/member")
	public String MemberView(Model model){
		System.out.println("test");
		List<Member> memberList = memberService.selectMember();
		System.out.println(memberList);
		model.addAttribute("list",memberList);
	    return "memberview";
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

