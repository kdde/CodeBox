package com.green.nowon.service.impl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.board.reply.ReplyListDTO;
import com.green.nowon.domain.dto.board.reply.ReplySaveDTO;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.ReplyEntity;
import com.green.nowon.domain.entity.board.ReplyEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.service.ReplyService;

@Service
public class ReplyServiceProc implements ReplyService{

	@Autowired
	private ReplyEntityRepository repo;
	
	//등록
	@Transactional
	@Override
	public void save(ReplySaveDTO dto) {
		
		ReplyEntity entity=ReplyEntity.builder()
				.content(dto.getContent())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.board(BoardEntity.builder().bno(dto.getBno()).build())
				.build();
		repo.save(entity);
	}

	//리스트 불러오기
	@Transactional
	@Override
	public void findAll(Model model) {
		
		List<ReplyListDTO> result= repo.findAll()
				.stream().map(ReplyListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("list",result);
		
	}

	@Transactional
	@Override
	public void delete(long rno) {
		repo.deleteById(rno);		
	}

}
