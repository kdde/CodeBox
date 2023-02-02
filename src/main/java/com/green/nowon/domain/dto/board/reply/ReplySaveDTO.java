package com.green.nowon.domain.dto.board.reply;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.ReplyEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ReplySaveDTO {
	
	private String content;
	private long mno;
	private long bno;
	private String writerName;
	
	public ReplyEntity toReplyEntity() {
		return ReplyEntity.builder()
				.content(content)
				.member(MemberEntity.builder().mno(mno).name(writerName).build())
				.board(BoardEntity.builder().bno(bno).build()).build();
	}

}
