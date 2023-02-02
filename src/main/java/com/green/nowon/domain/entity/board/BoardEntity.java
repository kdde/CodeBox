package com.green.nowon.domain.entity.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_board_seq",
		sequenceName = "gg_board_seq", initialValue = 1, allocationSize = 1)
@Table(name = "gg_board")
@Entity
public class BoardEntity extends BaseDateTimeColumns{

	@Id
	@GeneratedValue(generator = "gg_gen_board_seq", strategy = GenerationType.SEQUENCE)
	private long bno;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	private int readCount;
	//작성자 - MemeberEntity
	
	@JoinColumn(name = "mno")
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;//작성자
	
	
	@Builder.Default
	@OneToMany(mappedBy = "board")
	List<BoardImgEntity> imgs=new ArrayList<>();
	
	public String defImgUrl() {

		BoardImgEntity def = null;
		
		if(imgs.size()!=0) {
		def=imgs.get(0);
		};
		
		return def.getUrl()+def.getNewName();
	}
	
	//이미지 삽입 편의메서드
	public BoardEntity addImg(BoardImgEntity bimg) {
		imgs.add(bimg);
		return this;
	}
	
	//대표이미지만 추출하는 편의메서드
	public BoardImgEntity defImg() {
		for(BoardImgEntity bimg:imgs) {
			if(bimg.isDef()==true)
				return bimg;
		}
		BoardImgEntity def = null;
		if(imgs.size()!=0) {
		def=imgs.get(0);
		};
		return def;
		
		//이미지 없으면(사이즈 0) null
	}
	
	//편의메서드
	public BoardEntity update(BoardUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;

	}
}
