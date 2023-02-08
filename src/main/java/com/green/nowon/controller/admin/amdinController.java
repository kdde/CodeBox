package com.green.nowon.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.service.PositionService;
import com.green.nowon.service.attendance.CommuteService;



@Controller
public class amdinController {
	
	@Autowired
	private CommuteService commuteService;

	@Autowired
	private PositionService service;
	
	@GetMapping("/admin")
	public String admin(Principal principal,Model model ,CommuteInsertDTO idto,@RequestParam(defaultValue = "1") int page) {
		commuteService.showListTime(commuteService.MemberMno(principal), model , page);
		return "admin/ggAdmin";
	}
	
	@GetMapping("/adminlist")
	public String adminlist() {
		return "admin/admin-list";
	}
	
	@GetMapping("/admin/position")
	public String position() {
		return "/admin/position";
	}
	
	@PostMapping("/admin/position")
	public String addPosition(String name,int salary){
		System.out.println(name);
		service.save(name,salary);
		return "/admin/position";
	}
	
	@GetMapping("/admin/bonus")
	public String bonus() {
		return "/admin/bonus";
	}
	
}