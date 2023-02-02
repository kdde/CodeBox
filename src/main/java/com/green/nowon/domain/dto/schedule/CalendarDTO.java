package com.green.nowon.domain.dto.schedule;

import java.util.Date;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CalendarDTO {
	
	private String cTitle; 
	private Date cStartTime;
	private Date cEndTime;
	private long cno;
	private long mno;
	private String color;
	
	public CalendarEntity toCalendarEntity() {
		
		return CalendarEntity.builder()
				.cTitle(cTitle).cStartTime(cStartTime).cEndTime(cEndTime)
				.member(MemberEntity.builder().mno(mno).build())
				.color(color)
				.build();
	}

	public CalendarDTO(CalendarEntity e) {
		this.cTitle = e.getCTitle();
		this.cStartTime = e.getCStartTime();
		this.cEndTime = e.getCEndTime();
		this.cno = e.getCno();
		this.color = e.getColor();
	}
	
	

	
}
