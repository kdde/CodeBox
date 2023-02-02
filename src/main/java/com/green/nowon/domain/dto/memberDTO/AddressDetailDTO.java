package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.AddressEntity;

import lombok.Data;
/**
 * 
 * @author LeeYongJu
 *
 */
@Data
public class AddressDetailDTO {

	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	private boolean base;
	
	
	public AddressDetailDTO(AddressEntity e) {
		this.postcode = e.getPostcode();
		this.roadAddress = e.getRoadAddress();
		this.jibunAddress = e.getJibunAddress();
		this.detailAddress = e.getDetailAddress();
		this.extraAddress = e.getExtraAddress();
		this.base = e.isBase();
	}
}
