package com.team2.movie.dao.dto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.team2.movie.services.RepoService;

class WebConnection2{
	String json;
	WebConnection2() throws Exception{
		

//		String jsonInString = "";
//
//		// 가져와야할 json
		String urladd = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"; // moviedetail
		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
		
		String address = urladd + "?key=" + key;
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";

		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
//        테스트출력
		System.out.println(json);
	}
}

//public class MovieDetailJson {
//	public static void main(String[] args) throws Exception {
//		WebConnection2 wc = new WebConnection2();
//		
//		String json = wc.json;
//		
//		JSONParser parser = new JSONParser();
//		JSONObject obj = (JSONObject)parser.parse(json);
//		JSONObject channel = (JSONObject)obj.get("movieListResult");
//		JSONArray item = (JSONArray)channel.get("movieList");
//		
//		List <MovieDetail> movieDetailList = new ArrayList<>();
//
//		for(int i=0;i<item.size();i++){
//			JSONObject tmp = (JSONObject)item.get(i);
//			String movieCd = (String)tmp.get("movieCd");
//			String movieNm = (String)tmp.get("movieNm");
//			String movieNmEn = (String)tmp.get("movieNmEn");
//			String prdtYear = (String)tmp.get("prdtYear");
//			String openDt = (String)tmp.get("openDt");
//			String prdtStatNm = (String)tmp.get("prdtStatNm");
//			String nationAlt = (String)tmp.get("nationAlt");
//			String genreAlt = (String)tmp.get("genreAlt");
//			String repNationNm = (String)tmp.get("repNationNm");
////			String directors = (String)tmp.get("directors");
////			String companys = (String)tmp.get("companys");
//			
//			MovieDetail movieDetail = new MovieDetail();
//			movieDetail.setMovieCd(movieCd);
//			movieDetail.setMovieNm(movieNmEn);
//			movieDetail.setMovieNmEn(movieNmEn);
//			movieDetail.setPrdtYear(prdtYear);
//			movieDetail.setOpenDt(openDt);
//			movieDetail.setPrdtStatNm(prdtStatNm);
//			movieDetail.setNationAlt(nationAlt);
//			movieDetail.setGenreAlt(genreAlt);
//			movieDetail.setRepNationNm(repNationNm);
////			movieDetail.setDirectors(directors);
////			movieDetail.setCompanys(companys);
//	
//			movieDetailList.add(movieDetail);
//
//		}
//	}
//} //http://blog.naver.com/occidere/220799351272



//public class MovieDetailJson {
//	public String callMovieList() throws JsonProcessingException {
//		HashMap<String, Object> result = new HashMap<String, Object>();
//
//		String jsonInString = "";
//
//		// 가져와야할 json
//		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"; // moviedetail
//		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
//
//		try {
//			// RestTemplate 설정
//			// RestTemplate 이란? -> https://sjh836.tistory.com/141 참고
//
//			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//			factory.setConnectTimeout(5000); // 연결시간 초과, 타임아웃 설정 5초
//			factory.setReadTimeout(5000);// 읽기시간초과, 타임아웃 설정 5초
//			RestTemplate restTemplate = new RestTemplate(factory);
//
//			// HttpClient는 HTTP를 사용하여 통신하는 범용 라이브러리
//			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100) // connection pool 적용
//					.setMaxConnPerRoute(5) // connection pool 적용
//					.build();
//			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
//
//			HttpHeaders header = new HttpHeaders(); // 헤더에 key들을 담아준다
//			HttpEntity<?> entity = new HttpEntity<>(header);
//
//			// 요청할 주소
//			// movie main 의 정보, 여기서 movieCd 값을 찾아 받는다
//			UriComponents uri = UriComponentsBuilder
//					.fromHttpUrl(url + "?key=" + key)
//					.build();
//
//			// API를 호출해 MAP타입으로 전달 받는다.
//			// RestTemplate은 HttpClient 를 추상화(HttpEntity의 json, xml 등)해서 제공
//
//			// exchage : HTTP header setting 가능, ResponseEntity로 반환
//			// RequeResponseEntity -> return body obj, header obj, status code
//			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
//			result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인
//			result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
//			result.put("body", resultMap.getBody()); // 실제 데이터 정보 확인
//
////				HashMap<String, List<String>>  // 하나의 키에 여러개의 밸류
//			// LinkedHashMap 입력 순서대로 저장
//			LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("movieListResult");
//
//			// dailybox office list 결과를 리스트로 출력, 리스트 안의 내용은 map 형식 <string 키, list 타입 value)
//			ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("searchMovieList");
//
//			LinkedHashMap mnList = new LinkedHashMap<>(); // K object, V object
//
//			for (Map obj : dboxoffList) {
//				mnList.put(obj.get("movieCd"), obj.toString()); // 여기서 movieNm, openDt, rank, 만 꺼내야하는데.....????
////				mnList.put(obj.get("rnum"), obj.get("movieNm"));
//
//			}
//
//			// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌 object to json
//			ObjectMapper mapper = new ObjectMapper();
//			jsonInString = mapper.writeValueAsString(mnList);
//
////				json to object
////				User user = mapper.readValue(jsonInString, User.class);
//
//		} catch (HttpClientErrorException | HttpServerErrorException e) {
//			result.put("statusCode", e.getRawStatusCode());
//			result.put("body", e.getStatusText());
//			System.out.println(e.toString());
//
//		} catch (Exception e) {
//			result.put("statusCode", "999");
//			result.put("body", "excpetion오류");
//			System.out.println(e.toString());
//		}
//		return jsonInString;
//	}
//}