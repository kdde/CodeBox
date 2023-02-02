package com.green.nowon.domain.dto.board;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenBoardSaveDTO {
	
	private String title; 
	private String content;
	private long mno;
	
	//셋팅된 dto data를 Entity객체로 변환
	public GeneralBoardEntity toGeneralBoardEntity() {
		return GeneralBoardEntity.builder()
				.title(title).content(content)
				//.member(MemberEntity.builder().mno(mno).build())
				.mno(mno)
				.build();
	}
	
}
