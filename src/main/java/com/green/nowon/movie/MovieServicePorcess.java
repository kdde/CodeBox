package com.green.nowon.movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.nowon.naver.movie.NaverMovieResponse;
import com.green.nowon.openapi.OpenApiUtil;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import lombok.val;

@Service
public class MovieServicePorcess implements MovieService {

	private final String key="11ff91e57e98e3cac398ded6bb8f8c4f";
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Value("${navar.api.movie.client-id}")
	private String movieKey;
	@Value("${navar.api.movie.client-secret}")
	private String movieSecret;
	
	private KobisOpenAPIRestService kobis;
	public MovieServicePorcess(){
		kobis=new KobisOpenAPIRestService(key);
	}
	
	@Override
	public List<Item> dailyBoxOfficeList(String targetDt) {
		//boolean isJson; 
		//String targetDt=null; 
		String itemPerPage=null;
		String multiMovieYn=null;
		String repNationCd=null; 
		String wideAreaCd=null;
		
		//필수 파라미터 문자열 yyyyMMdd 전날까지 
		if(targetDt==null) {
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate yesterday=LocalDate.now().minusDays(1);
			//System.out.println(yesterday.format(formatter));//2023-01-31 --> 20230131
			targetDt=yesterday.format(formatter);//날짜 저장
		}
		
		ObjectMapper objectMapper=new ObjectMapper();
		MovieResponse response=null;
		try {
			//JSON형식의 문자열데이터로 결과 리턴
			String result=kobis.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);
			//System.out.println(result);
			//json 문자열데이터를 -> 클래스에 매핑
			response=objectMapper.readValue(result, MovieResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//테스트
		//String serviceKey="0788f028-d6fb-4a71-a08c-8ab1dba2a5e8";
		//String serviceKey="21752504-35b3-4121-855e-99bc6e8ac6da";//일별박스오피스
		
		//String apiUrl="http://api.kcisa.kr/openapi/service/rest/meta16/getkobis01";
		//apiUrl +="?serviceKey="+serviceKey;
		
		//http://api.kcisa.kr/openapi/service/rest/meta4/getKFAC0403?serviceKey=0788f028-d6fb-4a71-a08c-8ab1dba2a5e8
		//http://api.kcisa.kr/openapi/service/rest/meta16/getkobis01?serviceKey=21752504-35b3-4121-855e-99bc6e8ac6da
		
		
		//'class="fl thumb"'
		
		//한국영상자료원
		//https://www.koreafilm.or.kr/member/login
		//KMDB
		//https://www.kmdb.or.kr/info/api/apiDetail/6
		
		//인증키:460V1DOI5NK0I4379V66
		
		
		
		return response.getBoxOfficeResult().getDailyBoxOfficeList().stream()
				
				.map(i->{
					String imgurl=naverApiMovie(i.getMovieNm(), i.getOpenDt());
					return i.imgUrl(imgurl);
				})
				.collect(Collectors.toList());
	}

	//네이버api 에서 영화이미지를 획득하기위한 목적이었음
	private String naverApiMovie(String query, String openDt) {
		openDt=openDt.substring(0, 4);
		System.out.println(openDt);
		
		try {
			query =URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		String apiUrl = "https://openapi.naver.com/v1/search/movie.json?query="+query;
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", movieKey);
        requestHeaders.put("X-Naver-Client-Secret", movieSecret);
        requestHeaders.put("Content-Type", "application/json");
        
        String responseString=OpenApiUtil.request(apiUrl, requestHeaders, "GET");
        
        ObjectMapper objectMapper=new ObjectMapper();
        NaverMovieResponse response=null;
        try {
        	response=objectMapper.readValue(responseString, NaverMovieResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        /*
        for(com.nowon.green.naver.movie.Item item:response.getItems()) {
        	System.out.println(item);
        }
        */
        return response.getItems().get(0).getImage();
        
	}
	

}
