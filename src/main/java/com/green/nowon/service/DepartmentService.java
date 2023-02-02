package com.green.nowon.service;

import org.springframework.ui.Model;

public interface DepartmentService {

	boolean isReg(String text);

	void save(String[] name);

	void departmentList(Long parentDno, Model model);

	void departmentMemberList(Long dno, Model model);

}
