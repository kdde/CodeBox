package com.green.nowon.domain.dto.chatbot;

import com.green.nowon.domain.dto.PhoneInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerDTO {
	
	private long no;
	private String content;
	private String keyword;//name
	
	private PhoneInfo phone;
	
	public AnswerDTO phone(PhoneInfo phone){
		this.phone=phone;
		return this;
	}

}