package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.ProfileEntity;

import lombok.Data;

@Data
public class ProfileDTO {
	
	private long pno;
	private String url;
	private String orgName;
	private String newName;
	private String plusImg;
	
	public ProfileDTO(ProfileEntity e) {
		pno=e.getPno();
		url=e.getUrl();
		orgName=e.getOrgName();
		newName=e.getNewName();
		plusImg=e.getUrl()+e.getNewName();
	}
}
