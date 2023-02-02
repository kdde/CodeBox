package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author LeeYongJu
 * 권한 허가 : 모든 페이지("/**") , /css/** ,"/images/**","/js/**"
 * email , pass 로 아이디와 비밀번호 입력
 */

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.authorizeRequests(authorize->authorize
 				.antMatchers("/css/**","/images/**","/js/**").permitAll()
 				.antMatchers("/log/signup").permitAll()
 				.antMatchers("/**","/naver/**").permitAll()
 				.antMatchers(HttpMethod.PUT ,"/notice-boards/**", "/boards/**").hasRole("USER")
				.antMatchers(HttpMethod.DELETE ,"/notice-boards/**", "/boards/**").hasRole("USER")
				.antMatchers(HttpMethod.POST,"/notice-boards", "/boards").hasRole("USER")
				.antMatchers(HttpMethod.GET,"/admin/boards-registration").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET,"/general/boards-registration").hasRole("USER")
				.antMatchers("/","/notice-boards/**", "/boards/**").permitAll()
				
 				
 				
 				.anyRequest().authenticated()
 				)

 				.formLogin(login-> login
 							.loginPage("/login")//로그인 페이지
							.loginProcessingUrl("/signin")//form의 action값
							.usernameParameter("id")
							.passwordParameter("pass")
							.failureUrl("/login")//로그인 실패 시 다시 로그인페이지로 이동
							.defaultSuccessUrl("/admin") //로그인 성공 후 인덱스페이지로 이동
							
							.permitAll()
							)
 				.csrf(csrf->csrf.disable()
		 					)
 		;		
 		return http.build();
 	}
	
}
