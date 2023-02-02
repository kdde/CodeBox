package com.green.nowon.domain.entity.cate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * @author LeeYongJu
 * 직책 카테고리 
 * 컬럼 : pno(직책번호) , postionName(직책 이름) ,normalSalary(직책 기본금)
 * 카테고리형식이기 때문에 직접 추가 혹은 추가하는 부분이 필요
 * 1차 카테고리이기에 셀프조인 삭제 -> 부모 fk 삭제
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Gg_position")
@Entity
public class PositionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pno;//직책 번호
	
	@Column(nullable = false)
	private String pName;//직책 이름
	
	@Column(nullable = false)
	private int normalSalary;//기본금
	
}