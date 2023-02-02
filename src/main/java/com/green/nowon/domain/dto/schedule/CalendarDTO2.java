package com.green.nowon.domain.dto.schedule;

import java.util.Date;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity2;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CalendarDTO2 {
	
	private String title; 
	private Date startTime;
	private Date endTime;
	private long cno2;
	
	public CalendarEntity2 toCalendarEntity() {
		
		return CalendarEntity2.builder()
				.title(title).startTime(startTime).endTime(endTime)
				.build();
	}

	public CalendarDTO2(CalendarEntity2 e) {
		this.title = e.getTitle();
		this.startTime = e.getStartTime();
		this.endTime = e.getEndTime();
		this.cno2 = e.getCno2();
	}
	
	

	
}
