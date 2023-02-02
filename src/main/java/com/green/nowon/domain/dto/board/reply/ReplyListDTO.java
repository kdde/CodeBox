package com.green.nowon.domain.dto.board.reply;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.ReplyEntity;

import lombok.Getter;

@Getter
public class ReplyListDTO {

	private long rno;
	private long bno;
	private long mno;
	private String content;
	private String writerName;
	private String writerId;
	private LocalDateTime createdDate;
	
	//reply entity를 dto로 바꿔주기
	public ReplyListDTO(ReplyEntity e) {
		
		this.rno=e.getRno();
		this.bno=e.getBoard().getBno();
		this.mno=e.getMember().getMno();
		this.content=e.getContent();
		this.writerName=e.getMember().getName();
		this.writerId=e.getMember().getId();
		this.createdDate=e.getCreatedDate();
		
	}
}
