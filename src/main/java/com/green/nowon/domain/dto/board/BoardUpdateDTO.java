package com.green.nowon.domain.dto.board;

import java.util.ArrayList;
import java.util.List;

import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.BoardImgEntity;
import com.green.nowon.util.MyFileUtils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BoardUpdateDTO {
	
	private String title;
	private String content;
	
	//이미지
	private String[] newName;
	private String[] orgName;

	//메서드 만들어두고 서비스에서 실행할거임
	public List<BoardImgEntity> toListImgs(BoardEntity entity, String url) {
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
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
	}
	
	public BoardEntity updateEntity() {
		return BoardEntity.builder()
				.title(title)
				.content(content)
			.build();
	}
	
}
