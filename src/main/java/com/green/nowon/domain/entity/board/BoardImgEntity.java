package com.green.nowon.domain.entity.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.entity.BaseDateEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "board")
@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_seq_nb_img",
sequenceName = "gg_seq_nb_img", initialValue = 1, allocationSize = 1)
@Table(name = "gg_nboard_picture")
@Entity
public class BoardImgEntity extends BaseDateEntity{

	@Id
	@GeneratedValue(generator = "gg_gen_seq_nb_img", strategy = GenerationType.SEQUENCE)
	private long fno;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	
	private boolean def;

	//대표이미지를 세팅해주는 편의메서드
	public BoardImgEntity def(boolean def) {
		this.def=def;
		return this;
	}
	
	@OnDelete(action= OnDeleteAction.CASCADE)//삭제 cascade를 하여 삭제 실패
	@JoinColumn//bno
	@ManyToOne
	private BoardEntity board;
	
}
