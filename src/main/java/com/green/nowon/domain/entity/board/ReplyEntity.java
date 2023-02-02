package com.green.nowon.domain.entity.board;

import javax.persistence.CascadeType;
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

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@SequenceGenerator(name = "gg_gen_reply_seq", sequenceName = "gg_reply_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gg_reply")
@Entity
public class ReplyEntity extends BaseDateTimeColumns{
	
	@Id
	@GeneratedValue(generator = "gg_gen_reply_seq", strategy = GenerationType.SEQUENCE)
	private long rno;
	@Column(nullable = false)
	private String content;
	
	@JoinColumn(name = "mno")
	@ManyToOne(cascade = CascadeType.DETACH)
	private MemberEntity member;
	
	
	@JoinColumn(name = "bno")
	@ManyToOne(cascade = CascadeType.DETACH)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BoardEntity board;

	//private String writerName; dto에서 처리
	
}
