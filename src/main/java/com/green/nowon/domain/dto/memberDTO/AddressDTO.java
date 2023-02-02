package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.AddressEntity;

import lombok.Getter;

@Getter
public class AddressDTO {
	
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	private boolean base;
	
	private MemberDTO member;
	
	public AddressDTO(AddressEntity e) {
		postcode = e.getPostcode();
		roadAddress = e.getRoadAddress();
		jibunAddress = e.getJibunAddress();
		detailAddress = e.getDetailAddress();
		extraAddress = e.getExtraAddress();
		member= new MemberDTO(e.getMember());
		base = e.isBase();
	}
	
}
