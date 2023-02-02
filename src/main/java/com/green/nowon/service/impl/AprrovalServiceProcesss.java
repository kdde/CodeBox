package com.green.nowon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;
import com.green.nowon.domain.dto.attendance.AttendanceSaveDTO;
import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.approval.ApprovalEntityRepository;
import com.green.nowon.domain.entity.attendance.AttendanceEntity;
import com.green.nowon.domain.entity.attendance.AttendanceEntityRepository;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.service.ApprovalService;

@Service
public class AprrovalServiceProcesss implements ApprovalService{

	@Autowired
	ApprovalEntityRepository repo;
	
	@Autowired
	AttendanceEntityRepository adrepo;
	@Autowired
	DepartmentMemberEntityRepository dRepo;
	
	@Override
	public void save(ApprovalSaveDTO dto) {
		repo.save(ApprovalEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.date(dto.getDate())
				.status("결재 대기중")
				.adno(null)
				.mno(MemberEntity.builder()
						.mno(dto.getMno())
						.build())
				.build());
		
	}

	@Override
	public void list(Model model, int page) {
		int size=10;
		Sort sort=Sort.by(Direction.DESC, "ano");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<ApprovalEntity> result=repo.findAll(pageable);
		
		model.addAttribute("page",result);
		model.addAttribute("list", result
				.stream()
				.map(ApprovalListDTO::new)
				.collect(Collectors.toList()));		
	}

	@Override
	public void detail(long ano, Model model) {
		model.addAttribute("list", repo.findById(ano).map(ApprovalListDTO::new)
				.orElseThrow());
		List<DepartmentMemberEntity> abc=dRepo.findByMemberMno(repo.findById(ano).get().getMno().getMno());
		DepartmentMemberEntity ab=abc.get(abc.size()-1);
		model.addAttribute("dName",ab.getDepartment().getDname());
	}

	@Override
	public void ok(long ano) {
			Optional<ApprovalEntity> approval=repo.findById(ano);
			
			approval.ifPresent(e -> {
				e.setStatus("결재 승인");
				repo.save(e);
			});
			
		
	}

	@Override
	public void save(AttendanceSaveDTO dto) {		
		repo.save(ApprovalEntity.builder()
				.content(dto.getContent())
				.date(dto.getDate())
				.title(dto.getAdtype())
				.status("결재 대기중")
				.adno(AttendanceEntity.builder()
						.adtype(dto.getAdtype())
						.sdate(dto.getSdate())
						.edate(dto.getEdate())
						.build())
				.mno(MemberEntity.builder()
						.mno(dto.getMno())
						.build())
				
				.build());
		
	}

	@Override
	public void findMax(Model model) {
		List<ApprovalEntity> lap=repo.findAll();
		
		model.addAttribute("number", lap.get(lap.size()-1).getAno());
	}

	@Override
	public void list(Model model, long mno,int page) {
		int size=10;
		Sort sort=Sort.by(Direction.DESC,"ano");
		Pageable pageable=PageRequest.of(page-1, size,sort);
		
		Page<ApprovalEntity> result=repo.findByMno_Mno(mno,pageable);
		model.addAttribute("mno",mno);
		model.addAttribute("page",result);
		model.addAttribute("list", result
				.stream()
				.map(ApprovalListDTO::new)
				.collect(Collectors.toList()));	
		
	}

	@Override
	public void refuse(long ano) {
		Optional<ApprovalEntity> approval=repo.findById(ano);
		
		approval.ifPresent(e -> {
			e.setStatus("결재 거부됨");
			repo.save(e);
		});
		
	}



	




}
