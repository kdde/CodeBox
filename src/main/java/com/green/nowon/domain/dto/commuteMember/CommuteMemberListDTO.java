package com.green.nowon.domain.dto.commuteMember;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class CommuteMemberListDTO {
	
	private String gTime;
	
	private String oTime;
	
	private LocalDate today;
	
	private String cType;
	
	private MemberEntity member;
	
	private String name;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("D일 a h시간 mm분");
	
	public CommuteMemberListDTO(CommuteEntity e) {
		if(e==null) {
			this.gTime = LocalDateTime.now().format(formatter);
			this.oTime = LocalDateTime.now().format(formatter);
			this.today = LocalDate.now();
			this.cType = "첫출근";
			this.name = "첫출근";
		}else {
			this.gTime = e.getGTime().format(formatter);
			this.oTime = e.getOTime().format(formatter);
			this.today = e.getToday();
			this.cType = e.getCType();
			this.name = e.getMember().getName();
		}
	}
}
