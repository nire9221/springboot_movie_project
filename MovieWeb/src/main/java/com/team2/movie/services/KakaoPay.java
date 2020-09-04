package com.team2.movie.services;

import java.net.URI;
import java.net.URISyntaxException;
 

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.team2.movie.dao.dto.KakaoPayApprovalVO;
import com.team2.movie.dao.dto.KakaoPayReadyVO;

import lombok.extern.java.Log;
 


@Service
@Log
public class KakaoPay {
 
    private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private String moviename,name;
    
    public String kakaoPayReady(String title,String name) {
    	System.out.println("kakaopay 처음부분");
        RestTemplate restTemplate = new RestTemplate();
        moviename=title;
        this.name=name;
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9dc1dc65209a1ac0b4f53ba7c056e068");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1");	//ticketid
        params.add("partner_user_id", name);	//session.name
        params.add("item_name", title);		//title
        params.add("quantity", "1");			//seatselect로 받은 seat개수
        params.add("total_amount", "16000");	//quantity*가격
        params.add("tax_free_amount", "10000");	//임의값
        params.add("approval_url", "http://114.200.5.121:65532/kakaoPaySuccess");
        params.add("cancel_url", "http://114.200.5.121:65532/kakaoPayCancel");
        params.add("fail_url", "http://114.200.5.121:65532/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            System.out.println(kakaoPayReadyVO);
            System.out.println(kakaoPayReadyVO.getNext_redirect_pc_url());
            log.info("" + kakaoPayReadyVO);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("여기 오냐?");
        return null;//여기 오면 실패임
        
    }
    
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
 
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9dc1dc65209a1ac0b4f53ba7c056e068");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1");	//ticketid
        params.add("partner_user_id", name);	//session.id
        params.add("pg_token", pg_token);
        params.add("total_amount", "16000");
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);
            System.out.println("kakaoPayApprovalVO");
            return kakaoPayApprovalVO;
        
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
}