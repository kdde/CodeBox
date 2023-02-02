package com.green.nowon.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;

@Service
public class EmployeeServiceProc implements EmployeeService{

	@Autowired
	private DepartmentEntityRepository deRepo;
	
	
	@Override
	public void listView(Long parentDno, Model model) {
		if(parentDno.intValue()==0)parentDno=null;
		List<DepartmentEntity> result=deRepo.findAllByParentDno(parentDno);
		model.addAttribute("list",result);
		System.err.println(">>>>>>>>>>>>>"+result);
		
		
		
	}

}
