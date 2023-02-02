package com.green.nowon.domain.dto.board;


import com.green.nowon.domain.entity.board.BoardImgEntity;

import lombok.Getter;

@Getter
public class BoardImgDTO {
	
	private long fno;
	private String orgName;
	private String newName;
	private String url;
	private boolean defImg;
	//편의필드
	private String imgUrl;
	
	private String orgImgUrl;
	
	public BoardImgDTO(BoardImgEntity e) {
		this.fno = e.getFno();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.url = e.getUrl();
		this.defImg = e.isDef();
		
		this.imgUrl = url+newName;
		this.orgImgUrl = url+orgName;
	}


}
