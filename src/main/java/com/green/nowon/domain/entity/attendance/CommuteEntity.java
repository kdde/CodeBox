package com.green.nowon.domain.entity.attendance;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import ch.qos.logback.core.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Gg_Commute")
@Entity
public class CommuteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cno;
	
	@CreationTimestamp//출근
	private LocalDateTime gTime;
	
	@UpdateTimestamp//퇴근
	private LocalDateTime oTime;

	@CreationTimestamp
	private LocalDate today;
	
	@Column(columnDefinition = "VARCHAR(255) default '출근'")
	private String cType;
	
	@JoinColumn(name = "mno")
	@ManyToOne
	private MemberEntity member;
	
	public CommuteEntity fkSaver(MemberEntity member) {
		this.member=member;
		return this;
	}
	
	public CommuteEntity update(CommuteUpdateDTO dto,long cType) {
		this.oTime = dto.getOTime();
		if(cType <9L) {
			this.cType = "조퇴";
		}else {
			this.cType = "퇴근";
		}
		return this;
	}
	
	@PrePersist
	public void ctype() {
		this.cType = this.cType == null ? "출근" : this.cType;
	}

	
}
