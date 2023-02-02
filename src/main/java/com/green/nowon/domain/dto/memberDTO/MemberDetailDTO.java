package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.dto.position.PositionDTO;
import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class MemberDetailDTO {
	
	private long mno;
	private String id;
	private String name;
	private String pass;
	private String phone;
	
	public MemberDetailDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
	
	}
	
}
