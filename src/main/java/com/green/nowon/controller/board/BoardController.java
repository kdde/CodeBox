package com.green.nowon.controller.board;

import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;
import com.green.nowon.domain.dto.board.reply.ReplySaveDTO;
import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.BoardService;
import com.green.nowon.service.ReplyService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyService serv;

	//공지사항 게시판
	
	@GetMapping("/notice-boards")
	public String board(@RequestParam(defaultValue = "1") int page , Model model) {//문자열로 파라미터 매핑--> int형 parse
		service.getListAll(page ,model);
		return "board/noticeList";
	}
	
	@GetMapping("/admin/boards-registration")
	public String boardReg() {
		return "board/adminWrite";
	}
	
	//등록
	@PostMapping("/notice-boards")        
	public String write(BoardSaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno());
		service.save(dto);		
		return "redirect:/notice-boards";
	}
	
	@ResponseBody//응답데이터를 json타입으로 리턴
	@PostMapping("/admin-notice/temp-upload")
	public Map<String,String> tempUpload(MultipartFile bimg) {
		return service.fileTempUpload(bimg);
	}
	
	
	//공지사항 상세 페이지
	@GetMapping("/notice-boards/{bno}")
	public String detail(@PathVariable long bno, Model model) {
		service.updateReadCount(bno);  //조회수(레포지토리에 쿼리추가후)
		service.sendDetail(bno, model);
		
		serv.findAll(model); // 댓글 목록
		return "board/noticeDetail";
	}
	
	//삭제
	@DeleteMapping("/notice-boards/{bno}")
	public String delete(@PathVariable long bno, long rno, int flag) {
		if(flag==1){
			service.delete(bno);
			return "redirect:/notice-boards";}
		else {
			serv.delete(rno);
			return "redirect:/notice-boards/{bno}";}
	}
	
	//수정
	@PatchMapping("/notice-boards/{bno}")                 //setter 있어야함.
	public String update(@PathVariable long bno, BoardUpdateDTO dto) {
		service.update(bno, dto);
		return "redirect:/notice-boards";
	}
	
	//검색 및 페이징까지
	@GetMapping("/notice-boards/search")
    public String search(String keyword, Model model, @RequestParam(defaultValue = "1") int page) {
        service.search(keyword, model, page);
        return "board/noticeSearchPage";
    }
	
	//댓글등록
	@PostMapping("/notice-boards/{bno}")
	public String replyWrite(@PathVariable long bno, ReplySaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno());
		serv.save(dto);
		return "redirect:/notice-boards/{bno}";
	}
	
	//댓글 리스트 @GetMapping("/notice-boards/{bno}") 증복오류
	/*
	 * @GetMapping("/notice-boards/{bno}") public String replyList(@PathVariable
	 * long bno, Model model) { serv.findAll(model); return
	 * "redirect:/notice-boards/{bno}"; }
	 */
	
	
	
	/* 여기서부터 자유게시판 */
	
	@GetMapping("/boards")
	public String genBoard(@RequestParam(defaultValue = "1") int page , Model model) {
		service.getListAll02(page ,model);
		return "board/generalList";
	}
	
	/*
	 * 검색기능 ajax 활용 방법
	 * 
	 * @GetMapping("/boards/search")
	 * public String genSearch(String keyword, Model model, int page) {
	 * 
	 * service.search02(keyword, model, page);
	 * 
	 * return "board/generalSearchPage";
	 * }
	 */
	
	//검색
	@GetMapping("/boards/search")
	public String genSearch(String keyword, Model model, @RequestParam(defaultValue = "1") int page) {
		service.search02(keyword, model, page);
		return "board/genSearchPage";
	}
	
	
	@GetMapping("/general/boards-registration")
	public String genBoardReg() {
		return "board/generalWrite";
	}
	
	@PostMapping("/boards")        
	public String genWrite(GenBoardSaveDTO dto, Authentication auth) {
		MyUserDetails myUserDetails=(MyUserDetails)auth.getPrincipal();
		dto.setMno(myUserDetails.getMno()); //DTO에 @Setter를 이용하여 받아 올 수 있다.
		service.save02(dto);		
		return "redirect:/boards";
	}
	
	//자유게시판 상세페이지
	@GetMapping("/boards/{bno}")
	public String genDetail(@PathVariable long bno, Model model) {
		service.genUpdateReadCount(bno);  //조회수(레포지토리에 쿼리추가후)
		service.sendDetail02(bno, model);
		return "board/generalDetail";
	}
	
	//삭제
	@DeleteMapping("/boards/{bno}")
	public String genDelete(@PathVariable long bno) {
		
		service.delete02(bno);
		return "redirect:/boards";
	}
	
	//수정
	@PutMapping("/boards/{bno}")                 //setter 있어야함.
	public String genUpdate(@PathVariable long bno, GenBoardUpdateDTO dto) {
		service.update02(bno, dto);
		return "redirect:/boards/{bno}";
	}
	
}
