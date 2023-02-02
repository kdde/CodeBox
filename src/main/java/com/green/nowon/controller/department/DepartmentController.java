package com.green.nowon.controller.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.DepartmentService;
import com.green.nowon.service.impl.DepartmentServiceProc;

@Controller
public class DepartmentController {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 이미 존재하는지 확인
	 * @param text
	 * @return
	 */
	@ResponseBody
	@GetMapping("/admin/departments/{text}")
	public boolean isPresent(@PathVariable String text) {
		return departmentService.isReg(text);
	}
	/**
	 * 부서 추가등록
	 * @param name
	 * @return
	 */
	@PostMapping("/admin/departments")
	public String departmentReg(String[] name) {
		departmentService.save(name);
		return "admin/departmentCate/reg";
	}
	/**
	 * 부서 추가등록 페이지 이동
	 * @return
	 */
	@GetMapping("/admin/departmentRegist")
	public String departmentRegPage() {
		return "admin/departmentCate/reg";
	}
	/**
	 * 부서 리스트(조직도)페이지 이동
	 * @return
	 */
	@GetMapping("/admin/departments")
	public String departmentList() {
		return "admin/departmentList/departmentList";
	}
	/**
	 * 부서 리스트 띄어주는 메서드 ajax
	 * @param parentDno
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/departmentsList/{parentDno}")
	public String category(@PathVariable long parentDno, Model model) {
		departmentService.departmentList(parentDno, model);
		return "admin/departmentCate/ol-category";
	}
	
	@GetMapping("/member/departmentMemberList/{dno}")
	public String departmentMemberList(@PathVariable long dno, Model model) {
		departmentService.departmentMemberList(dno , model);
		return "admin/departmentList/department_member-list";
	}
}
