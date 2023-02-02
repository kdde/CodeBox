package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.reply.ReplySaveDTO;

public interface ReplyService {

	void save(ReplySaveDTO dto);

	void findAll(Model model);

	void delete(long rno);

}
