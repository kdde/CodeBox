package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.approval.ApprovalListDTO;
import com.green.nowon.domain.dto.approval.ApprovalSaveDTO;
import com.green.nowon.domain.dto.attendance.AttendanceSaveDTO;

public interface ApprovalService {

	void save(ApprovalSaveDTO dto);

	void list(Model model, int page);

	void detail(long ano, Model model);

	void ok(long ano);

	void save(AttendanceSaveDTO dto);

	void findMax(Model model);

	void refuse(long ano);

	void list(Model model, long mno, int page);

}
