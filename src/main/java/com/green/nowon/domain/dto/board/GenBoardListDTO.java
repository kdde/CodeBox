package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;

@Getter
public class GenBoardListDTO {
	
	private long bno;
	private String title;
	private int readCount;
	//private String writer;
	private LocalDateTime updatedDate;
	private LocalDate toDay;
	private String writerId;
	
	//Entity를 -> BoardListDTO(BoardEntity ent)
	public GenBoardListDTO(GeneralBoardEntity ent) {
		
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.readCount = ent.getReadCount();
		//this.writer = ent.getMember().getName(); //member의 name이 작성자임
		this.updatedDate = ent.getUpdatedDate();
		toDay=LocalDate.now();
		this.writerId =String.valueOf(ent.getMno());//.getId();
	}
	
}