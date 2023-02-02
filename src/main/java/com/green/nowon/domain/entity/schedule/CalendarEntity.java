package com.green.nowon.domain.entity.schedule;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.nowon.domain.dto.schedule.CalendarDTO;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_calendar_seq",
sequenceName = "gg_calendar_seq", initialValue = 1, allocationSize = 1)
@Table(name="ggCalendar")
@Entity
public class CalendarEntity {
	
	@Id
	@GeneratedValue(generator = "gg_gen_calendar_seq", strategy = GenerationType.SEQUENCE)
	private long cno;
	
	@Column(nullable = false)
	private String cTitle;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date cStartTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date cEndTime;
	
	@Column(nullable = false)
	private String color;
	
	@JoinColumn(name = "mno")
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;
	
	public CalendarEntity save(CalendarDTO dto){
		this.cTitle =  dto.getCTitle();
		this.cStartTime = dto.getCStartTime();
		this.cEndTime = dto.getCEndTime();
		return this;
	}
	
	
	
	
}


