package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

/**
 * @author LeeYongJu
 * 기본 날짜관련 설정 entity
 */

@Getter
@MappedSuperclass
public class BaseDateEntity {
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	
}
