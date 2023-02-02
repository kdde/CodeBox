package com.green.nowon.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {
	
	USER("ROLE_USER"),//사원
	ADMIN("ROLE_ADMIN");//팀장
	
	private final String role;
	
}
