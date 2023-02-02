package com.green.nowon.domain.dto.memberDTO;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInsertDTO {
	
	private String id;
	private String pass;
	private String name;
	private String phone;
	private String hireDate;
	//
	private Integer totSalary;
	private String email;
	private ProfileDTO profile;
	
	//비밀번호
	public MemberEntity signin(PasswordEncoder pe) {
		return MemberEntity.builder()
				.id(id)
				.name(name)
				.pass(pe.encode(pass))//비밀번호 암호화
				.phone(phone)
				.totSalary(0)
				.email(id+"@greengames.shop")
				.hireDate(LocalDate.parse(hireDate))
				.build();
	}
	
	public MemberInsertDTO signin2(MemberEntity e,PasswordEncoder pe) {
		id = e.getId();
		pass = pe.encode(e.getPass());
		phone = e.getPhone();
		totSalary = e.getTotSalary();
		email = e.getEmail();
		hireDate = e.getHireDate().toString();
		profile= new ProfileDTO(e.getProfile());
		return this;
	}
	

}
