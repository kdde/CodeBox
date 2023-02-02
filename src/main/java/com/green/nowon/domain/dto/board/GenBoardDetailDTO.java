package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;

import lombok.Getter;

@Getter
public class GenBoardDetailDTO {
	
	private long bno;
	private String title;
	private String content;
	private int readCount;
	//private String writerName;
	private String writerId;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private LocalDate toDay;
	private long mno;
	
	public GenBoardDetailDTO(GeneralBoardEntity ent) {
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.content = ent.getContent();
		this.readCount = ent.getReadCount();
		//this.writerName =ent.getMember().getName();
		//this.writerId =String.valueOf(ent.getMno());//.getId();
		this.createdDate = ent.getCreatedDate();
		this.updatedDate = ent.getUpdatedDate();
		toDay=LocalDate.now();
		this.mno=ent.getMno();
	}
	
	public void setWriterId(String id) {
		this.writerId=id;
	}

}
