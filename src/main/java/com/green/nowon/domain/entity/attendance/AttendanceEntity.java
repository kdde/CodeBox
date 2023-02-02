package com.green.nowon.domain.entity.attendance;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ggAttendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class AttendanceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adno;
	
	private Date sdate;
	
	private Date edate;
	
	private String adtype;
	
	
	@ManyToOne
	private MemberEntity mno;

}
