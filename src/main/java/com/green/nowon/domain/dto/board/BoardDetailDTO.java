package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.green.nowon.domain.entity.board.BoardEntity;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BoardDetailDTO {
	
	private long bno;
	private String title;
	private String content;
	private int readCount;
	private String writerName;
	private String writerId;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private LocalDate toDay;
	
	private String defImgUrl;
	
	private String orgImgUrl;
	
	private List<BoardImgDTO> imgs;
	
	public BoardDetailDTO(BoardEntity ent) {
		this.bno = ent.getBno();
		this.title = ent.getTitle();
		this.content = ent.getContent();
		this.readCount = ent.getReadCount();
		this.writerName =ent.getMember().getName();
		this.writerId =ent.getMember().getId();
		this.createdDate = ent.getCreatedDate();
		this.updatedDate = ent.getUpdatedDate();
		toDay=LocalDate.now();
		
		imgs=ent.getImgs().stream()
				.map(BoardImgDTO::new)
				.collect(Collectors.toList());
		
		if(ent.defImg()!=null) {
		this.defImgUrl = ent.defImg().getUrl()+ent.defImg().getNewName();
		
		this.orgImgUrl = ent.defImg().getUrl() + ent.defImg().getOrgName();
		} // 이미지없어도(null) 불러오기위한 조치
		
		}
	
	

}
