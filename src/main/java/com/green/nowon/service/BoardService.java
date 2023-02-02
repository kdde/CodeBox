package com.green.nowon.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;

public interface BoardService {
	
	Map<String,String> fileTempUpload(MultipartFile bimg);

	void getListAll(int page, Model model);

	void sendDetail(long bno, Model model);
	
	void save(BoardSaveDTO dto, String name);

	void save(BoardSaveDTO dto);

	void delete(long bno);

	void update(long bno, BoardUpdateDTO dto);

	void getListAll02(int page, Model model);

	void sendDetail02(long bno, Model model);	
	
	void save02(GenBoardSaveDTO dto, String name);

	void save02(GenBoardSaveDTO dto);

	void delete02(long bno);

	void update02(long bno, GenBoardUpdateDTO dto);

	void myGetListAll02(Model model,long mno);
	
	int updateReadCount(Long bno); //조회수

	int genUpdateReadCount(Long bno); //자유게시판 조회수

	//void search(String keyword,  Model model);

	//void search02(String keyword, Model model);

	void search(String keyword, Model model, int page);

	void search02(String keyword, Model model, int page);

	void myGetListAll(int page, Model model);

}
