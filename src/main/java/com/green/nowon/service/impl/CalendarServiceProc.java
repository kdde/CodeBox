package com.green.nowon.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.schedule.CalendarDTO;
import com.green.nowon.domain.dto.schedule.CalendarDTO2;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity;
import com.green.nowon.domain.entity.schedule.CalendarEntity2;
import com.green.nowon.domain.entity.schedule.CalendarEntity2Repository;
import com.green.nowon.domain.entity.schedule.CalendarEntityRepository;
import com.green.nowon.service.CalendarService;

@Service
public class CalendarServiceProc implements CalendarService {

	//캘린더엔티티
	@Autowired
	private CalendarEntityRepository crepo;
	
	//회사캘린더엔티티
	@Autowired
	private CalendarEntity2Repository crepo2;

	// 캘린더 insert
	@Override
	public long save(long userMno, CalendarDTO dto) {
		CalendarEntity entity=CalendarEntity.builder()
				.cTitle(dto.getCTitle()).cStartTime(dto.getCStartTime()).cEndTime(dto.getCEndTime()).cno(dto.getCno())
				.member(MemberEntity.builder().mno(userMno).build())
				.color(dto.getColor())
				.build();
		
		return crepo.save(entity).getCno();
		
	}
	
	// 캘린더 select
	@Override
	public List<CalendarDTO> getList(long userMno) {
		return crepo.findAllByMember_mno(userMno).stream()
				.map(CalendarDTO::new)
				.collect(Collectors.toList());
	}

	// 캘린더 Delete
	@Override
	public void delete(long cno) {
		crepo.deleteById(cno);
		
	}

	//캘린더 Update
	@Override
	public void update(long userMno, long cno, CalendarDTO dto) {
		CalendarEntity entity=CalendarEntity.builder()
				.cTitle(dto.getCTitle()).cStartTime(dto.getCStartTime()).cEndTime(dto.getCEndTime()).cno(cno)
				.member(MemberEntity.builder().mno(userMno).build())
				.color(dto.getColor())
				.build();
		crepo.save(entity);
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////회사일정///////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////

	// 캘린더 select
	@Override
	public List<CalendarDTO2> getList2() {
		return crepo2.findAll().stream()
				.map(CalendarDTO2::new)
				.collect(Collectors.toList());
	}
	
	// 캘린더 insert
	@Override
	public long save(CalendarDTO2 dto) {
		CalendarEntity2 entity = CalendarEntity2.builder()
				.title(dto.getTitle()).startTime(dto.getStartTime()).endTime(dto.getEndTime()).cno2(dto.getCno2())
				.build();
		
		return crepo2.save(entity).getCno2();
	}
	
	// 캘린더 Delete
		@Override
		public void delete2(long cno2) {
			System.out.println("proc"+cno2);
			crepo2.deleteById(cno2);
			
		}

}
