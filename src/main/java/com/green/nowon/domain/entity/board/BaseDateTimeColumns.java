package com.green.nowon.domain.entity.board;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseDateTimeColumns {
	
	//timestamp(6)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
}
