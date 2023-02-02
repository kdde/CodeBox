package com.green.nowon.service.mypage;

import org.springframework.ui.Model;

public interface MyPageService {


	void info(long mno, Model model);


	void list(long mno, Model model);

	void salaryInfo(long mno, Model model);

	void update(long mno, long pno, long dno);


	void update2(long mno, double plSal, Integer totSal);

	

	
}
