package com.green.nowon.domain.entity.chatbot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.green.nowon.domain.dto.chatbot.AnswerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
@Entity
public class Answer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	private String content;
	
	private String keyword;//name
	
	public Answer keyword(String keyword) {
		this.keyword=keyword;
		return this;
	}
	
	public AnswerDTO toAnswerDTO() {
		return AnswerDTO.builder()
				.no(no).content(content).keyword(keyword)
				.build();
	}

}