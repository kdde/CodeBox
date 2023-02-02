package com.green.nowon.domain.dto.approval;

import java.sql.Date;

import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class ApprovalListDTO {
	
	private long ano;

	private String title;
	
	private String content;
	
	private Date date;
		
	private String status;
	
	private MemberEntity mno;
	
	private long mnoo;
	
	private String mName;
	
	public ApprovalListDTO(ApprovalEntity e) {
		this.date=e.getDate();
		this.ano=e.getAno();
		this.title=e.getTitle();
		this.content=e.getContent();
		this.status=e.getStatus();
		
		if(e.getMno()!=null) {
		this.mno=e.getMno();
		this.mnoo=mno.getMno();
		this.mName=mno.getName();
		}
		
	}
	
}
