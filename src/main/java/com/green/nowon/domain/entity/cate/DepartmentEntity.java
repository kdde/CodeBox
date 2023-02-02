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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author LeeYongJu
 * table 명 : Gg_department
 * 컬럼 : dno(부서번호) , dname(부서명) , depth(레벨) , parent(상위부서)
 * 카테고리이면서 3차 까지 있기 때문에 셀프 조인을 하기 위해서 본인(DepartmentEntity)을 참조 하였습니다.
 * depth를 통해서 몇차 카테고리인지 확인 가능
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Gg_department")
@Entity
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dno;
	private String dname;
	private int depth;
	
	@JoinColumn
	@ManyToOne//parent_dno
	private DepartmentEntity parent; 
	
}
