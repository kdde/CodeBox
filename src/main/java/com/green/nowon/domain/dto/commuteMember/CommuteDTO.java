package com.green.nowon.domain.dto.commuteMember;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.green.nowon.domain.dto.memberDTO.MemberDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;

@Getter
public class CommuteDTO {
	
	private long cno;
	
	private String gTime;
	
	private String oTime;
	
	private LocalDate today;
	
	private String cType;
	
	private MemberDTO member;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d일 a h시간 mm분");
	
	public CommuteDTO(CommuteEntity e ) {
		cno = e.getCno();
		gTime = e.getGTime().format(formatter);
		oTime = e.getOTime().format(formatter);
		today = e.getToday();
		cType = e.getCType();
		member = new MemberDTO(e.getMember());
	}
}
