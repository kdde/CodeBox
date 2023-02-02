package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.DepartmentEntity;

import lombok.Data;

@Data
public class DepartmentDTO {

	private String dname;
	
	private long dno;
	
	public DepartmentDTO(DepartmentEntity e) {
		this.dname=e.getDname();
		this.dno=e.getDno();
	}
}
