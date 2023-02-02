package com.green.nowon.domain.dto.position;

import com.green.nowon.domain.entity.cate.PositionEntity;

import lombok.Data;

@Data
public class PositionDTO {

	private long pno;
	
	private String pName;
	
	private int normalSalary;
	
	public PositionDTO(PositionEntity e) {
		this.pno=e.getPno();
		this.pName=e.getPName();
		this.normalSalary=e.getNormalSalary();
		
	}
}
