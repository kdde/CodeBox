package com.green.nowon.domain.dto.memberDTO;

import org.springframework.util.StringUtils;

import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class SalaryListDTO {
	
	private long mno;
	private String id;
	private String email;
	private String name;
	private String pass;
	private String phone;
	
	private String profileUrl;
	private String newName;
	private String orgName;
	
	
	private PositionEntity pno;
	private String pname;
	
	
	private int normalSalary;

	
	
	//감소되는금액
	private double minSal;
	
	//증가하는금액
	private int none;
	private double sal1y;
	private double sal2y;
	private double sal3y;
	private double sal4y;
	private double sal5y;
	
	//nomalSal에서 감소되는금액을 뺀 금액
	private int minSalTot;
	
	//사원관리페이지에서 급여등록 후 멤버엔티티에 저장되는 컬럼
	private double bonus;//증가되는 금액
	private int totSal;//모든걸 계산한 후 최종급여
	
	String role;
	
	public SalaryListDTO(MemberEntity e){
		this.mno=e.getMno();
		this.id=e.getId();
		this.name = e.getName();
		this.pass = e.getPass();
		this.phone=e.getPhone();
		this.email=e.getEmail();
		this.role=e.getRoles().toString();

		if(e.getPno()!=null) {	//직책,기본금,마이너스,플러스금액 계산

			this.pname=e.getPno().getPName();
			this.normalSalary=e.getPno().getNormalSalary();
			
			this.minSal=e.getPno().getNormalSalary()*0.09;//-금액 고정
			
			this.none=0;//신입
			this.sal1y=e.getPno().getNormalSalary()*0.045;//기본급의 3% 1년차
			this.sal2y=e.getPno().getNormalSalary()*0.09;//기본급의 6% 2년차
			this.sal3y=e.getPno().getNormalSalary()*0.135;//기본급의 9% 3년차
			this.sal4y=e.getPno().getNormalSalary()*0.18;//기본급의 12% 4년차
			this.sal5y=e.getPno().getNormalSalary()*0.225;//기본급의 15% 5년차
			
			this.minSalTot=(int) (e.getPno().getNormalSalary()-minSal);//세금이 포함된 월급
			
			this.bonus=e.getBoList();
			this.totSal=e.getTotSalary();
			
		}else{
			this.pname="없음";
			this.normalSalary=0;
			
			this.minSal=0;//세금
			
			this.sal1y=0;
			this.sal2y=0;
			this.sal3y=0;
			this.sal4y=0;
			this.sal5y=0;
			
			this.minSalTot=0;
			
			this.bonus=0;
			this.totSal=0;
			
			
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 스타트겟프로파일");
		

		
		
		
		
		if(e.getProfile()!=null) {
			this.profileUrl=e.getProfile().getUrl()+e.getProfile().getNewName();
			this.orgName = e.getProfile().getOrgName();
			this.newName = e.getProfile().getNewName();

		}

	}	

}