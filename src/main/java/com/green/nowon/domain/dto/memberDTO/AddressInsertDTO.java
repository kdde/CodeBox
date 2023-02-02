package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.AddressEntity;

import lombok.Setter;

@Setter
public class AddressInsertDTO {
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	
	
	public AddressEntity signin() {
		return AddressEntity.builder()
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.build();
	}

}
