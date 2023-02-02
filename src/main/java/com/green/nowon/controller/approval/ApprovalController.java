package com.green.nowon.controller.approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;
import com.green.nowon.domain.dto.attendance.AttendanceSaveDTO;
import com.green.nowon.service.ApprovalService;

@Controller
public class ApprovalController {

	@Autowired
	ApprovalService approvalService;
	
	
	@GetMapping("/approval")
	public String approval(Model model) {
		approvalService.findMax(model);
		return "/approval/approval";
	}
	
	@PostMapping("/approval/save")
	public String save(ApprovalSaveDTO dto) {
		approvalService.save(dto);
		return "redirect:/approval/check/"+dto.getMno();
	}
	
	@PostMapping("/approval/vacation")
	public String vacation(AttendanceSaveDTO dto) {
		approvalService.save(dto);
		
		return "redirect:/approval/check/"+dto.getMno();
	}
	
	@GetMapping("/approval/check/{mno}")
	public String list(Model model,@PathVariable long mno,@RequestParam(defaultValue = "1") int page) {
		approvalService.list(model,mno,page);
		return "/approval/check";
	}
	
	@GetMapping("/approval/sure")
	public String sure(Model model,@RequestParam(defaultValue = "1") int page) {
		approvalService.list(model,page);
		return "/approval/sure";
	}
	
	@GetMapping("/approval/detail/{ano}")  
	public String detail(@PathVariable long ano,Model model) {
		approvalService.detail(ano,model);
		return "/approval/detail";
	}
	
	@GetMapping("/approval/vacation")
	public String vacation(Model model) {
		approvalService.findMax(model);
		return "/approval/vacation";
	}
	
	@PatchMapping("/approval/ok/{ano}")
	public String ok(@PathVariable long ano) {
		approvalService.ok(ano);
		return "redirect:/approval/sure";
	}
	
	@PatchMapping("/approval/refuse/{ano}")
	public String refuse(@PathVariable long ano) {
		approvalService.refuse(ano);
		return "redirect:/approval/sure";
	}
}
