package com.team2.movie.dao.dto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.team2.movie.dao.api.MovieMainDao;

class WebConnection {
	String json;
	WebConnection() throws Exception {
		//
//			// 가져와야할 json
		String urladd = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"; // moviemain
		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String today = sdf.format(c1.getTime());
		String targetDate = today;

		String address = urladd + "?key=" + key + "&targetDt=" + targetDate;
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";

		url = new URL(address);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
//	        테스트출력
//			System.out.println(json);
	}
}

public class MovieMainJson {
	
	public List<MovieMain> MovieMainJsonList() throws Exception {
		WebConnection wc = new WebConnection();
		String json = wc.json;
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(json);
		JSONObject channel = (JSONObject) obj.get("boxOfficeResult");
		JSONArray item = (JSONArray) channel.get("dailyBoxOfficeList");

		List<MovieMain> movieMainList = new ArrayList<>();
		
		for (int i = 0; i < item.size(); i++) {
			
			JSONObject tmp = (JSONObject) item.get(i);
			MovieMain movie = new MovieMain();
			
			movie.setMovieId((long) 1);
			movie.setRnum((String) tmp.get("rnum"));
			movie.setRank((String) tmp.get("rank"));
			movie.setRankInten((String) tmp.get("rankInten"));
			movie.setRankOldAndNew((String) tmp.get("rankOldAndNew"));
			movie.setMovieCd((String) tmp.get("movieCd"));
			movie.setMovieNm((String) tmp.get("movieNm"));
			movie.setOpenDt((String) tmp.get("openDt"));
			movie.setSalesAmt((String) tmp.get("salesAmt"));
			movie.setSalesShare((String) tmp.get("salesShare"));
			movie.setSalesInten((String) tmp.get("salesInten"));
			movie.setSalesChange((String) tmp.get("salesChange"));
			movie.setSalesAcc((String) tmp.get("salesAcc"));
			movie.setAudiCnt((String) tmp.get("audiCnt"));
			movie.setAudiInten((String) tmp.get("audiInten"));
			movie.setAudiChange((String) tmp.get("audiChange"));
			movie.setAudiAcc((String) tmp.get("audiAcc"));
			movie.setScrnCnt((String) tmp.get("scrnCnt"));
			movie.setShowCnt((String) tmp.get("showCnt"));

//			System.out.println(movie);
//			movieMainList.add(movie);
//			
//			RepoService repo = new RepoService();
//			repo.saveMovie(movie);
		}
		return movieMainList;
	}

	public static void main(String[] args) throws Exception {
		MovieMainJson test = new MovieMainJson();
		System.out.println(test.MovieMainJsonList());
	}
	
} // http://blog.naver.com/occidere/220799351272










//	public String callBoxOffice() throws JsonProcessingException {
//		HashMap<String, Object> result = new HashMap<String, Object>();
//
//		String jsonInString = "";
//
//		// 가져와야할 json
//		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"; // moviemain
//		String key = "ab4da447d209d1ad3bcce6ca4e89ef03";
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Calendar c1 = Calendar.getInstance();
//		String today = sdf.format(c1.getTime());
//		String targetDate = today;
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
//			UriComponents uri = UriComponentsBuilder.fromHttpUrl("url+?key=" + key + "&targetDt=" + targetDate).build();
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
////			HashMap<String, List<String>>  // 하나의 키에 여러개의 밸류
//			// LinkedHashMap 입력 순서대로 저장
//			LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("movieListResult");
//
//			// dailybox office list 결과를 리스트로 출력, 리스트 안의 내용은 map 형식 <string 키, list 타입 value)
//			ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("searchMovieList");
//
//			LinkedHashMap mnList = new LinkedHashMap<>(); // K object, V object
//
//			List<MovieMain> movieMain = new ArrayList<>();
//
//			for (Map obj : dboxoffList) {
//				MovieMain movie = new MovieMain();
//				movie.setSalesShare((String) obj.get("salesShare"));
//				movie.setSalesInten((String) obj.get("salesInten"));
//				movie.setSalesChange((String) obj.get("salesChange"));
//				movie.setSalesAcc((String) obj.get("salesAcc"));
//				movie.setAudiCnt((String) obj.get("audiCnt"));
//				movie.setAudiInten((String) obj.get("audiInten"));
//				movie.setAudiChange((String) obj.get("audiChange"));
//				movie.setAudiAcc((String) obj.get("audiAcc"));
//				movie.setShowCnt((String) obj.get("showCnt"));
//			}
//
//			// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌 object to json
//			ObjectMapper mapper = new ObjectMapper();
//			jsonInString = mapper.writeValueAsString(mnList);
//
////			json to object
////			User user = mapper.readValue(jsonInString, User.class);
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
//
//	public static void main(String[] args) {
//		MovieMainJson movieMainJson = new MovieMainJson();
//		try {
//			movieMainJson.callBoxOffice();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
//}
