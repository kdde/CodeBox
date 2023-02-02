package com.green.nowon.controller.commute;

import java.awt.print.Pageable;
import java.security.Principal;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.attendance.CommuteService;


@Controller
public class CommuteController {

	@Autowired
	private CommuteService commuteService;
	
	
	@GetMapping("/admin/commute")
	public String goTimepage() {
		return "/admin/commute";
	}
	
	/**
	 * 
	 * @param mno	: ${#authorize.principal.mno}
	 * @return 업데이트한 시간을 나타내기 위한 페이지
	 */
	@ResponseBody
	@PostMapping("/admin/commute/btn/{mno}")
	public String offTime(@PathVariable long mno,CommuteInsertDTO dto, CommuteUpdateDTO udto,boolean plag) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d일 a hh시 mm분");
		String date = "";
		commuteService.save(mno,dto,udto);//update기능도 포함되어 있습니다.
		if(plag) {
			date = commuteService.findTodayTime(mno).get().getGTime().format(formatter);
		}else {
			date= commuteService.findLastTime(mno,dto).get().getOTime().format(formatter);
		}
		//System.err.println(commuteService.findGoTime(mno).get().getGTime().toLocalTime());
		return date;
	}
	
	@GetMapping("/member/commute/list")
	public String commuteList(Principal principal,Model model) {
		long mno = commuteService.MemberMno(principal);
		commuteService.showListTime(mno,model,0);
		return "admin/ggAdmin";
	}
	

}
