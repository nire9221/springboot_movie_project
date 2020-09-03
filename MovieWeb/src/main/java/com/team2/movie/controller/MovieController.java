package com.team2.movie.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
public class MovieController {

	@Autowired
	MovieMainDao movieMainDao;

	@Autowired
	MovieDetailDao movieDetailDao;

//--------------------------------------영화 API-----------------------------------------
	// 메인페이지 영화 정보
	@GetMapping("/updateMovie")
	public String callBoxOffice() throws JsonProcessingException {

		HashMap<String, Object> result = new HashMap<String, Object>();
		String jsonInString = "";

		// 가져와야할 json
		String url = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"; // movieemain
		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Calendar cal = Calendar.getInstance();
//		String today = sdf.format(cal.getTime());
		Date dDate = new Date();
		dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String yesterday = dSdf.format(dDate);

		String targetDate = yesterday;

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
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "?key=" + key + "&targetDt=" + targetDate)
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

			List<MovieMain> movieMain = new ArrayList<>();

//			if(movie.getRank().isEmpty() || !movie.getRank().equals(obj.get("rank"))) { 
			// 중복데이터 처리해줘야하는데...

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

				movieMainDao.save(movie);
				movieMain.add(movie);
			}
//			System.out.println("movie List : nawara  " + movieMain);

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

//--------------------movie detail-------------------------
	@GetMapping("/updateDetail")
	public String getMovieDetail() throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String jsonInString = "";
		String urladd = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"; // moviedetail
		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
//	      MovieMain movie = new MovieMain();
//	      movieCd = movie.getMovieCd();
		String movieCd = "20201122";

		try {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(5000); // 연결시간 초과, 타임아웃 설정 5초
			factory.setReadTimeout(5000);// 읽기시간초과, 타임아웃 설정 5초
			RestTemplate restTemplate = new RestTemplate(factory);

			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100) // connection pool 적용
					.setMaxConnPerRoute(5).build();
			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅

			HttpHeaders header = new HttpHeaders(); // 헤더에 key들을 담아준다
			HttpEntity<?> entity = new HttpEntity<>(header);

			UriComponents uri = UriComponentsBuilder.fromHttpUrl(urladd + "?key=" + key + "&movieCd=" + movieCd)
					.build();

			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

			result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인
			result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
			result.put("body", resultMap.getBody()); // 실제 데이터 정보 확인

			LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("movieInfoResult");
			HashMap detailmap = (HashMap) lm.get("movieInfo");
			LinkedHashMap mnList = new LinkedHashMap<>(); // K object, V object

//			System.out.println("detailmap: " + detailmap);

			MovieDetail movieDetail = new MovieDetail();
			movieDetail.setMovieCd((String) detailmap.get("movieCd"));
			movieDetail.setMovieNm((String) detailmap.get("movieNm"));
			movieDetail.setMovieNmEn((String) detailmap.get("movieNmEn"));
			movieDetail.setMovieNmOg((String) detailmap.get("movieNmog"));
			movieDetail.setPrdtYear((String) detailmap.get("prdtYear"));
			movieDetail.setShowTm((String) detailmap.get("showTm"));
			movieDetail.setOpenDt((String) detailmap.get("openDt"));
			movieDetail.setPrdtStatNm((String) detailmap.get("prdtStatNm"));
			movieDetail.setTypeNm((String) detailmap.get("typeNm"));

			ArrayList<Map> gmap = (ArrayList<Map>) detailmap.get("genres");
			for (int i = 0; i < gmap.size(); i++) {
				Map<String, String> ggmap = gmap.get(i);
//				movieDetail.setGenre(ggmap);
				String genre = (String) ggmap.get("genreNm");
				movieDetail.setGenreNm(genre);
//				System.out.println(nation);
//				System.out.println("nation : " + nnmap.get("nationNm"));
			}

			ArrayList<Map> nmap = (ArrayList<Map>) detailmap.get("nations");
			for (int i = 0; i < nmap.size(); i++) {
				Map<String, String> nnmap = nmap.get(i);
//				movieDetail.setNation(nnmap);
				String nation = (String) nnmap.get("nationNm");
				movieDetail.setGenreNm(nation);
//				System.out.println(nation);
//				System.out.println("nation : " + nnmap.get("nationNm"));
			}
			ArrayList<Map> amap = (ArrayList<Map>) detailmap.get("actors");
			for (int i = 0; i < amap.size(); i++) {
				Map<String, String> aamap = amap.get(i);
//				movieDetail.setActor(aamap);
				String actorname = (String) aamap.get("peopleNm");
				movieDetail.setActorNm(actorname);
//				System.out.println(actorname);
//				System.out.println("actor name : " + aamap.get("peopleNm") + " ename : " + aamap.get("peopleNmEn")
//						+ " cast : " + aamap.get("cast") + " castEn : " + aamap.get("castEn"));
			}
			ArrayList<Map> cmap = (ArrayList<Map>) detailmap.get("companys");
			for (int i = 0; i < cmap.size(); i++) {
				Map<String, String> ccmap = cmap.get(i);
//				movieDetail.setCompany(ccmap);

				movieDetail.setCompanyNm(ccmap.get("companyNm"));
//				System.out.println("company cd : " + ccmap.get("companyCd") + " name : " + ccmap.get("companyNm")
//						+ " ename : " + ccmap.get("companyNmEn") + " partname : " + ccmap.get("companyPartNm"));
			}
			ArrayList<Map> dmap = (ArrayList<Map>) detailmap.get("directors");
			for (int i = 0; i < dmap.size(); i++) {
				Map<String, String> ddmap = dmap.get(i);
//				movieDetail.setDirector(ddmap);
				movieDetail.setDirectorNm(ddmap.get("peopleNm"));
//				System.out.println("director name : " + ddmap.get("peopleNm") + " ename : " + ddmap.get("peopleNmEn"));
			}
			ArrayList<Map> umap = (ArrayList<Map>) detailmap.get("audits");
			for (int i = 0; i < umap.size(); i++) {
				Map<String, String> uumap = umap.get(i);
//				movieDetail.setAudit(uumap);
				movieDetail.setDirectorNm(uumap.get("watchGradeNm"));
//				System.out.println("audit num : " + uumap.get("auditNo") + " grade : " + uumap.get("watchGradeNm"));
			}

			movieDetailDao.save(movieDetail);

			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(mnList);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			result.put("statusCode", e.getRawStatusCode());
			result.put("body", e.getStatusText());
			e.printStackTrace();
			System.out.println(e.toString());

		} catch (Exception e) {
			result.put("statusCode", "999");
			result.put("body", "excpetion오류");
			e.printStackTrace();
			System.out.println(e.toString());
		}

		return jsonInString;
	}
}
