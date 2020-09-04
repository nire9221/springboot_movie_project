package com.team2.movie.controller;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.movie.dao.api.MovieDetailDao;
import com.team2.movie.dao.api.MovieMainDao;
import com.team2.movie.dao.dto.MovieDetail;
import com.team2.movie.dao.dto.MovieMain;
import com.team2.movie.dao.api.MemberDao;
import com.team2.movie.dao.dto.Member;
import com.team2.movie.services.KakaoPay;

import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class HomeController {
	
	@Autowired
	MovieMainDao movieMainDao;
	
	@Autowired
	MovieDetailDao movieDetailDao;
	
	@Autowired
	MemberDao memberdao;
	
	//get all movieList
	@GetMapping("/")
	public String movieMain(Model model) { 
		List<MovieMain> movieList = movieMainDao.findAll();
		model.addAttribute("movieList",movieList);
//		System.out.println("test"  + model.getAttribute("movieDetail"));
		return "main";
	}
	
	@GetMapping("/detailinfo")
	public String getMovieDetail(Model model, @RequestParam("movieCd") String movieCd) {
		MovieDetail movieDetailInfo = movieDetailDao.findByMovieCd(movieCd);
		System.out.println("movieCode : " + movieCd);
		System.out.println("moviedetail info : "+movieDetailInfo);
		model.addAttribute("movieDetail",movieDetailInfo);
		List<MovieMain> movieList = movieMainDao.findAll();
		model.addAttribute("movieList",movieList);
		System.out.println("movieList  : "+ movieList);
		
		return "main";  
	}
	
	
	@RequestMapping(value = "/login")
	public String loginPage() {
		return "home";
	}
	
	@PostMapping("/logout")
	public String logoutPage(HttpSession session) {
		session.invalidate();
		return "redirect:/";
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
		//System.out.println("token값 : "+token);
		session.setAttribute("token", token);
		// 노드에 로그아웃한 결과값음 담아줌 매개변수는 세션에 잇는 token을 가져와 문자열로 변환
		node = kr.Logout(session.getAttribute("token").toString());
		session.setAttribute("id", node.get("id"));
		
		
		if(memberdao.existsById(session.getAttribute("id").toString())) {
			System.out.println("회원인 경우");
			System.out.println(memberdao.getOne(session.getAttribute("id").toString()));
			session.setAttribute("membersession", memberdao.getOne(session.getAttribute("id").toString()));
			return "redirect:/";
		}
		
		return "signup";
	}
	
	@GetMapping("/signup")
	public String signup(@RequestParam("name") String name,@RequestParam("mail") String mail,@RequestParam("pnum") String pnum,@RequestParam("year") String year,@RequestParam("mon") String mon,@RequestParam("day") String day, HttpSession session) {
		Date birth = new Date();
		birth.setYear(Integer.parseInt(year));
		birth.setMonth(Integer.parseInt(mon));
		birth.setDate(Integer.parseInt(day));
		mail=mail.replace(",", "@");
		pnum=pnum.replace(",", "-");
		String id = session.getAttribute("id").toString();
		
		Member member = new Member();
		member.setKakaoId(id);
		member.setName(name);
		member.setBirth(birth);
		member.setPhone(pnum);
		member.setEmail(mail);

		memberdao.save(member);
		session.setAttribute("membersession",member);
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
//////////////////////////////////////////////////////////	
	
	@Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;    
	
	@GetMapping("/testpay")
	public String test() {
		return "kakaopay";
	}
	
//	@GetMapping("/kakaoPay")
//	public String kakaoTest() {
//		
//		return "redirect:" + kakaopay.kakaoPayReady();
//	}
    
    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam("title") String title,@RequestParam("time") String time, HttpSession session) {
        log.info("kakaoPay post............................................");
        System.out.println("여기로 오겠지");
        System.out.println(title+":"+time);
        Member mem = (Member) session.getAttribute("membersession");
        System.out.println(mem.getName());
        return "redirect:" + kakaopay.kakaoPayReady(title,mem.getName());
        //return "kakaopay.kakaoPayReady()";
    }

    
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
    	System.out.println("여기 오냐?");
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        
        //여기서 결제정보 ticket db에 넣어야함
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        
        return "ordercheck";
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
//    @GetMapping("/seatselect")
//    public String seat(@RequestParam("seat") String seat, Model model) {
//    	model.addAttribute("seat", seat);
//    	return "seatselect";
//    }
    
//    @GetMapping("/Ticket")
//    public String Ticket(@RequestParam("Ticket") String seat) {
//    	System.out.println();
//    	return "Ticket";
//    }
}
