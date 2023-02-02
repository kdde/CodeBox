package com.green.nowon.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;
import com.green.nowon.service.BoardService;
import com.green.nowon.service.MemberService;
import com.green.nowon.service.mypage.MyPageService;

@Controller
public class myPageController {
	
	@Autowired
	private MyPageService service;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/mypage/info/{mno}")
	public String myPageInfo(@PathVariable long mno,@RequestParam(defaultValue = "1") int page, Model model) {
		service.info(mno,model);
		boardService.myGetListAll(page, model);
		boardService.myGetListAll02(model,mno);
		return"mypage/mypage";
	}
	

	
	@PatchMapping("/mypage/{id}/update")
	public String update(@PathVariable long id, MemberUpdateDTO dto) {
		System.out.println("update patch 작동");
		memberService.update(id, dto);
		return "redirect:/admin/goods/list";
	}

}
