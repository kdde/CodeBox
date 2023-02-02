package com.green.nowon.domain.dto.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.attendance.CommuteEntity;

import lombok.Data;

@Data
public class CommuteUpdateDTO {
	
	private LocalDateTime gTime;
	private LocalDateTime oTime;
	private String cType;
	
	
	public CommuteEntity updateEntity() {
		return CommuteEntity.builder()
				.gTime(gTime)
				.oTime(oTime)
				.cType(cType)
				.build();
	}
	
}
