package com.green.nowon.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.employee.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;
	
	@GetMapping("/employee")//카테고리 리스트롤 사용할 예정
	public String employeeList() {
		return"member/employee-list";
	}
	
	@GetMapping("/employee/{parentDno}")
	public String employeeListCate(@PathVariable long parentDno,Model model) {
		eService.listView(parentDno,model);
		return "member/tag-list";
	}

}
