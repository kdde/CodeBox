package com.green.nowon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.DepartmentMemberListDTO;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.service.DepartmentService;

@Service
public class DepartmentServiceProc implements DepartmentService {

	@Autowired
	private DepartmentEntityRepository departmentRepo;
	
	@Autowired
	private DepartmentMemberRepository departmentMemberRepo;
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Override
	public boolean isReg(String text) {
		Optional<DepartmentEntity> result= departmentRepo.findByParentDnoNullAndDname(text);
		if(result.isEmpty())
			return true;
		return false;
	}

	@Override
	public void save(String[] names) {//3차 카테까지만 1차는 그린게임즈 고정
		DepartmentEntity cate1=departmentRepo.findByParentDnoAndDname(null, names[0])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[0]).depth(1).parent(null).build()));
		
		DepartmentEntity cate2=departmentRepo.findByParentDnoAndDname(cate1.getDno(), names[1])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[1]).depth(2).parent(cate1).build()));
		
		DepartmentEntity cate3=departmentRepo.findByParentDnoAndDname(cate2.getDno(), names[2])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[2]).depth(3).parent(cate2).build()));
	}

	/**
	 * 부서들 리스트 카테고리
	 */
	@Override
	public void departmentList(Long parentDno, Model model) {
//		if(parentDno.intValue()==0)parentDno=null;//null은 회사명
//		List<DepartmentEntity> list = departmentRepo.findAll();
		if(parentDno.intValue()==0)parentDno=null;
		List<DepartmentEntity> result= departmentRepo.findAllByParentDno(parentDno);
		
//		model.addAttribute("list", departmentRepo.findByParentDnoOrderByDnameAsc(parentDno));
		model.addAttribute("list", result);
	}
	/**
	 * 부서에 존재하는 유저 리스트
	 */
	@Transactional
	@Override
	public void departmentMemberList(Long dno, Model model) {//dno -> department_dno
		System.out.println("department member List 작동여부");
		List<DepartmentMemberListDTO> list = 
				departmentMemberRepo.findAllByDepartment_dno(dno)
				.stream().map(DepartmentMemberListDTO::new)
				.collect(Collectors.toList());
		
		model.addAttribute("srlist",list);
		
	}
}
