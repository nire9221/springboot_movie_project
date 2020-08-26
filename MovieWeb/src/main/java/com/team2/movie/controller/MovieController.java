package com.team2.movie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.movie.dao.api.MovieDetailDao;
import com.team2.movie.dao.api.MovieMainDao;
import com.team2.movie.dao.dto.MovieDetail;
import com.team2.movie.dao.dto.MovieMain;
import com.team2.movie.services.RepoService;

@Controller
public class MovieController {
	
	@Autowired
	MovieMainDao moviemaindao;

	@Autowired
	MovieDetailDao moviedetaildao;
	// 영화정보 업데이트
//	@RequestMapping(value = "/update")
//	public void updateMovieDB() throws JsonProcessingException {
//		MovieMainJson movieMainJson = new MovieMainJson();
//		MovieDetailJson movieDetailJson = new MovieDetailJson();
//		
//		movieMainJson.callBoxOffice();
//		movieDetailJson.callMovieList();
//	}

	/*
	 * jsp 페이지에서 구분자로 "/testPage/insert_data" 값으로 던져주었기 떄문에 해당 값으로 RequesetMapping을
	 * 합니다. post 형식으로 던졌기 때문에 post로 받아주기! (GET/POST 형식의 차이는 면접에서 자주 물어보는 기본 질문이니
	 * 숙지합시다 ㅎㅎ)
	 */
//	@RequestMapping(value = "/testPage/insert_data", method = RequestMethod.POST)

	// 메서드 작성하기 @Model 어노테이션을 통해 testPage 생성
//	public String insert_data(@ModelAttribute testPage testpage) {
//
//		System.out.print(testpage.toString()); // view에서 제대로 값 던져주는지 확인하기
//
//		return "redirect:/testPage"; // 요청 처리 후 testPage로 다시 연결
//	}

	// 메인페이지 영화 정보
	@GetMapping("/boxoffice")
	public String callBoxOffice() throws JsonProcessingException {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String jsonInString = "";

		// 가져와야할 json
		String url = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"; // movieemain

		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
		String targetDate = "20200801";
		
		// 페이지에 나타낼 갯수

		try {
			// RestTemplate 설정
			// RestTemplate 이란? -> https://sjh836.tistory.com/141 참고

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(5000); // 연결시간 초과, 타임아웃 설정 5초
			factory.setReadTimeout(5000);// 읽기시간초과, 타임아웃 설정 5초
			RestTemplate restTemplate = new RestTemplate(factory);

			// HttpClient는 HTTP를 사용하여 통신하는 범용 라이브러리
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100) // connection pool 적용
					.setMaxConnPerRoute(5) // connection pool 적용
					.build();
			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅

			HttpHeaders header = new HttpHeaders(); // 헤더에 key들을 담아준다
			HttpEntity<?> entity = new HttpEntity<>(header);

			// 요청할 주소
			// movie main 의 정보, 여기서 movieCd 값을 찾아 받는다
			UriComponents uri = UriComponentsBuilder
					.fromHttpUrl(url + "?key=" + key + "&targetDt=" + targetDate)
					.build();

			// API를 호출해 MAP타입으로 전달 받는다.
			// RestTemplate은 HttpClient 를 추상화(HttpEntity의 json, xml 등)해서 제공

			// exchage : HTTP header setting 가능, ResponseEntity로 반환
			// RequeResponseEntity -> return body obj, header obj, status code
			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인
			result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
			result.put("body", resultMap.getBody()); // 실제 데이터 정보 확인

//				HashMap<String, List<String>>  // 하나의 키에 여러개의 밸류
			// LinkedHashMap 입력 순서대로 저장
			LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("boxOfficeResult");

			// dailybox office list 결과를 리스트로 출력, 리스트 안의 내용은 map 형식 <string 키, list 타입 value)
			ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("dailyBoxOfficeList");

			LinkedHashMap mnList = new LinkedHashMap<>(); // K object, V object

			List <MovieMain> movieMain = new ArrayList<>();
			
			for (Map obj : dboxoffList) {
				MovieMain movie = new MovieMain();
				movie.setRnum((String) obj.get("rnum"));
				movie.setRank((String) obj.get("rank"));
				movie.setRankInten((String) obj.get("rankInten"));  
				movie.setRankOldAndNew((String) obj.get("rankOldAndNew"));  
				movie.setMovieCd((String) obj.get("movieCd"));  
				movie.setMovieNm((String) obj.get("movieNm"));
				movie.setOpenDt((String) obj.get("openDt"));
				movie.setSalesAmt((String) obj.get("salesAmt"));
				movie.setSalesShare((String) obj.get("salesShare"));
				movie.setSalesInten((String) obj.get("salesInten"));
				movie.setSalesChange((String) obj.get("salesChange"));
				movie.setSalesAcc((String) obj.get("salesAcc"));
				movie.setAudiCnt((String) obj.get("audiCnt"));
				movie.setAudiInten((String) obj.get("audiInten"));
				movie.setAudiChange((String) obj.get("audiChange"));
				movie.setAudiAcc((String) obj.get("audiAcc"));
				movie.setShowCnt((String) obj.get("showCnt"));
				
//				System.out.println(moviemaindao);
				moviemaindao.save(movie);
				movieMain.add(movie);
			}

//			// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌 object to json
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(mnList);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(e.toString());

		} catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body", "excpetion오류");
			System.out.println(e.toString());
		}
		
		return jsonInString;
	}
	@GetMapping("/boxoffice2")
	public String service() {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String jsonInString = "";
		
		String urladd = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"; // moviedetail
		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
		
		try {
			// RestTemplate 설정
			// RestTemplate 이란? -> https://sjh836.tistory.com/141 참고

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(5000); // 연결시간 초과, 타임아웃 설정 5초
			factory.setReadTimeout(5000);// 읽기시간초과, 타임아웃 설정 5초
			RestTemplate restTemplate = new RestTemplate(factory);

			// HttpClient는 HTTP를 사용하여 통신하는 범용 라이브러리
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100) // connection pool 적용
					.setMaxConnPerRoute(5) // connection pool 적용
					.build();
			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅

			HttpHeaders header = new HttpHeaders(); // 헤더에 key들을 담아준다
			HttpEntity<?> entity = new HttpEntity<>(header);

			// 요청할 주소
			// movie main 의 정보, 여기서 movieCd 값을 찾아 받는다
			UriComponents uri = UriComponentsBuilder
					.fromHttpUrl(urladd + "?key=" + key)
					.build();

			// API를 호출해 MAP타입으로 전달 받는다.
			// RestTemplate은 HttpClient 를 추상화(HttpEntity의 json, xml 등)해서 제공

			// exchage : HTTP header setting 가능, ResponseEntity로 반환
			// RequeResponseEntity -> return body obj, header obj, status code
			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인
			result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
			result.put("body", resultMap.getBody()); // 실제 데이터 정보 확인

//				HashMap<String, List<String>>  // 하나의 키에 여러개의 밸류
			// LinkedHashMap 입력 순서대로 저장
			LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("movieListResult");

			// dailybox office list 결과를 리스트로 출력, 리스트 안의 내용은 map 형식 <string 키, list 타입 value)
			ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("movieList");

			LinkedHashMap mnList = new LinkedHashMap<>(); // K object, V object

			List <MovieDetail> movieDetailList = new ArrayList<>();

			for(Map obj : dboxoffList){		
				MovieDetail movieDetail = new MovieDetail();
				movieDetail.setMovieCd((String)obj.get("movieCd"));
				movieDetail.setMovieNm((String)obj.get("movieNm"));
				movieDetail.setMovieNmEn((String)obj.get("movieNmEn"));
				movieDetail.setPrdtYear((String)obj.get("prdtYear"));
				movieDetail.setOpenDt((String)obj.get("openDt"));
				movieDetail.setPrdtStatNm((String)obj.get("prdtStatNm"));
				movieDetail.setNationAlt((String)obj.get("nationAlt"));
				movieDetail.setGenreAlt((String)obj.get("genreAlt"));
				movieDetail.setRepNationNm((String)obj.get("repNationNm"));
//				movieDetail.setDirectors((String)obj.get("directors"));
//				movieDetail.setCompanys((String)obj.get("companys"));
		
				moviedetaildao.save(movieDetail);
				movieDetailList.add(movieDetail);
				
//				RepoService repo = new RepoService();
//				repo.saveMovieDetail(movieDetail);
			}
			System.out.println(movieDetailList);

//			// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌 object to json
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(mnList);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			System.out.println(e.toString());

		} catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body", "excpetion오류");
			System.out.println(e.toString());
		}
		
		
		
		return jsonInString;
	}
}