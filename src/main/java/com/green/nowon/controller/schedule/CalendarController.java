package com.green.nowon.controller.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.schedule.CalendarDTO;
import com.green.nowon.domain.dto.schedule.CalendarDTO2;
import com.green.nowon.service.CalendarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	@Autowired
	CalendarService cService;

	// 본인 캘린더 페이지로 가기
	@GetMapping("/schedule/calendar")
	public String calendar() {
		return "/schedule/calendar";
	}

	// 캘린더 select
	@ResponseBody
	@GetMapping("/schedule/calendar/{userMno}")
	public List<CalendarDTO> calendarSelect(@PathVariable long userMno) {
		return cService.getList(userMno);
	}

	// 캘린더 insert
	@ResponseBody
	@PostMapping("/schedule/calendar/{userMno}")
	public long calendarInsert(@PathVariable long userMno, @RequestBody CalendarDTO dto, Model model) {
		System.out.println(dto);
		long cno = cService.save(userMno, dto);
		model.addAttribute("cno", cno);
		System.out.println("cno : " + cno);
		return cno;
	}

	// 캘린더 Delete
	@ResponseBody
	@DeleteMapping("/schedule/calendar/{userMno}")
	public String delete(@PathVariable long userMno, @RequestBody List<Map<String, Object>> param) {
		long cno = Integer.parseInt((String.valueOf(param.get(0).get("cno"))));
		cService.delete(cno);
		return "redirect:/schedule/calendar";
	}

	// 캘린더 Update
	@ResponseBody
	@PutMapping("/schedule/calendar/{userMno}")
	public String update(@PathVariable long userMno, @RequestBody CalendarDTO dto) {
		System.out.println(dto);
		long cno = dto.getCno();
		cService.update(userMno, cno, dto);
		return "/schedule/calendar";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////회사일정///////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	// 회사 캘린더 페이지로 가기
	@GetMapping("/schedule/companyCalenda")
	public String calendar2(Model model) {
		return "/schedule/companyCalendar";
		
	}
	
	// 캘린더 select
	@ResponseBody
	@GetMapping("/schedule/companyCalenda/list")
	public List<CalendarDTO2> calendarSelect2() {
		return cService.getList2();
	}
	
	// 캘린더 insert
	@ResponseBody
	@PostMapping("/schedule/companyCalenda/list")
	public long calendarInsert(@RequestBody CalendarDTO2 dto, Model model) {
		long cno2 = cService.save(dto);
		model.addAttribute("cno2", cno2);
		System.out.println("cno2 : " + cno2);
		return cno2;
	}
	
	// 캘린더 Delete
		@ResponseBody
		@DeleteMapping("/schedule/companyCalenda/list")
		public String delete(@RequestBody List<Map<String, Object>> param) {
			System.out.println("컨트"+param);
//			long cno2 = Integer.parseInt((String.valueOf(param.get(0).get("cno2"))));
//			cService.delete2(cno2);
			return "redirect:/schedule/companyCalenda";
		}
}
