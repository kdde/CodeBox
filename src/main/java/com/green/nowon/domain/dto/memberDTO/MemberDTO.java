package com.green.nowon.domain.dto.memberDTO;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;

@Getter
public class MemberDTO {
	
	private long mno;
	private String id;
	private String pass;
	private String name;
	private String phone;
	private String hireDate;
	private Integer totSalary;
	private String email;
	
	private ProfileDTO profileDTO;
	
	private String newName;
	private String orgName;
	
	
	private String profileUrl;
	
	//model로 뿌려주기가 편함
	public MemberDTO(MemberEntity e) {
		mno = e.getMno();
		name = e.getName();
	}
	
	//생성은 저장DTO를 만드는것이 편함
	//회원가입용 생성자DTO
	public MemberDTO(MemberEntity e , PasswordEncoder pe) {
		id = e.getId();
		pass = pe.encode(e.getPass());
		phone = e.getPhone();
		totSalary = e.getTotSalary();
		email = e.getEmail();
		hireDate = e.getHireDate().toString();
		
		newName = e.getProfile().getNewName();
		orgName = e.getProfile().getOrgName();
		if(e.getProfile()!=null) {
			profileUrl=e.getProfile().getUrl()+e.getProfile().getNewName();
		}
	}
}
