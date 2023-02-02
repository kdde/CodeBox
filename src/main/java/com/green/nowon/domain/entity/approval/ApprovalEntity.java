package com.green.nowon.domain.entity.approval;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.entity.attendance.AttendanceEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "ggApproval")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ApprovalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ano;
	
	private String title;
	
	private String content;
	
	private String status;
	
	private Date date;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	@ManyToOne
	private MemberEntity mno;
	
	@OneToOne
	@Cascade(CascadeType.PERSIST)
	private AttendanceEntity adno;
}
