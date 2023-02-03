package com.green.nowon.bus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.openapi.OpenApiUtil;

@Service
public class BusServiceProcess implements BusService {
	
	//DB 말고 openAPI
	
	@Value("${data.go.kr.bus.serviceKey}")
	private String serviceKey;
	
	@Override
	public void getBusPath(String strSrch, ModelAndView mv) {
		
		StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"); /*URL*/
        try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("strSrch","UTF-8") + "=" + URLEncoder.encode(strSrch, "UTF-8")); /*검색할 노선번호*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*[xml,json]*/
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
        
        String apiURL = urlBuilder.toString();
        //System.out.println(apiURL);
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-type", "application/json");
        
        String responseData = OpenApiUtil.request(apiURL, requestHeaders, "GET");
        
        System.out.println(responseData);
        
        
	}

}
