package com.green.nowon.domain.dto.board;

import java.util.ArrayList;
import java.util.List;



import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.BoardImgEntity;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.util.MybFileUtils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BoardSaveDTO {
	
	private String title; 
	private String content;
	private long mno;
	
	private String[] newName;
	private String[] orgName;
	
	public List<BoardImgEntity> toBoardListImgs(BoardEntity entity, String url) {
		List<BoardImgEntity> imgs = new ArrayList<>();
		for (int i = 0; i < orgName.length; i++) {
			if(orgName[i].equals("") || orgName[i]==null)continue;
			boolean def = false;
			if(i==0)def=true;
			BoardImgEntity bim = BoardImgEntity.builder()
					.url(url)
					.orgName(orgName[i])
					.newName(newName[i])
					.def(def)
					.board(entity)
					.build();
			imgs.add(bim);
		}
		//temp 폴더 상위 폴더인 upload로 이동
		MybFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
	}
	
	//셋팅된 dto data를 Entity객체로 변환
	public BoardEntity toBoardEntity() {
		return BoardEntity.builder()
				.title(title).content(content).member(MemberEntity.builder().mno(mno).build())
				.build();
	}


	
}
