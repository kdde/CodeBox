package com.green.nowon.domain.entity.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author LeeYongJu
 * 주소 관련 entity
 * DB 이름 : GgAddress
 * 컬럼 목록 : ano(주소번호) ,postcode(우편번호), roadAddress(도로명),jibunAddress(지번),
 * detailAddress(상세주소),extraAddress(추가주소),mno(직원번호),base(기본주소)
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "GgAddress")
@Entity
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ano;
	
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;

	private boolean base;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	@ManyToOne
	private MemberEntity member;
	public AddressEntity member(MemberEntity member) {
		this.member=member;
		return this;
	}
	
	public AddressEntity base(boolean base) {
		this.base=base;
		return this;
	}
}