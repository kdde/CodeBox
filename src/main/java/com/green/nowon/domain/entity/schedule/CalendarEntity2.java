package com.green.nowon.domain.entity.schedule;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.nowon.domain.dto.schedule.CalendarDTO2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_calendar2_seq",
sequenceName = "gg_calendar2_seq", initialValue = 1, allocationSize = 1)
@Table(name="ggCalendar2")
@Entity
public class CalendarEntity2 { //회사일정
	
	@Id
	@GeneratedValue(generator = "gg_gen_calendar2_seq", strategy = GenerationType.SEQUENCE)
	private long cno2;
	
	@Column(nullable = false)
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
	@Column(nullable = false)
	private Date startTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
	@Column(nullable = false)
	private Date endTime;
	
	public CalendarEntity2 save(CalendarDTO2 dto){
		this.title =  dto.getTitle();
		this.startTime = dto.getStartTime();
		this.endTime = dto.getEndTime();
		return this;
	}
	
}


