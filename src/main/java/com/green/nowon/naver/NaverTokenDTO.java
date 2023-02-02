package com.green.nowon.naver;

import lombok.Data;

@Data
public class NaverTokenDTO {
	
	private String access_token;
	private String refresh_token;
	private String scope;
	private String token_type;
	private String expires_in;
}
