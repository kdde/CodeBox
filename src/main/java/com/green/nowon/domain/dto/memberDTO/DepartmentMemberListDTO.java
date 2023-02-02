package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class DepartmentMemberListDTO {
	
	private long mno;
	private String id;
	private String name;
	private String pass;
	private String phone;
	private String email;
	private String profileUrl;
	
	//private String pName;
	private String dName;	
	
	public DepartmentMemberListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name=e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		this.email=e.getEmail();
		//this.profileUrl=e.getProfile().getUrl()+e.getProfile().getNewName();
		if(e.getProfile()!=null) {
			this.profileUrl=e.getProfile().getUrl()+e.getProfile().getNewName();
		}
		
	}
	public DepartmentMemberListDTO(DepartmentMemberEntity e) {
		this(e.getMember());
		this.dName=e.getDepartment().getDname();
	}
}
