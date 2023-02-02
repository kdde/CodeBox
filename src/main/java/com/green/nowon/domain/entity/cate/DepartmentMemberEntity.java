package com.green.nowon.domain.entity.cate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author LeeYongJu
 * 테이블명 : Gg_department_member
 * 컬럼 : dmno(부서직원번호) , department(부서테이블) , member(직원테이블)
 * 부서카테고리가 실질적으로 저장되는 부분
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Gg_department_member")
@Entity
public class DepartmentMemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dmno;
	
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn//category_cno
	@ManyToOne
	private DepartmentEntity department;
	
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn//goods_gno
	@ManyToOne
	private MemberEntity member;
}
