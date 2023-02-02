package com.green.nowon.domain.dto.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class CommuteInsertDTO {
	
//	private String cType;
	private LocalDateTime gTime;
	private LocalDateTime oTime;
	private LocalDate today;
	private MemberEntity member;
	
	public CommuteEntity entity() {
		return CommuteEntity.builder()
//				.cType(cType)
				.gTime(gTime)
				.oTime(oTime)
				.today(today)
				.build();
	}
	
}
