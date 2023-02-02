package com.green.nowon.domain.entity.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.dto.schedule.CalendarDTO;

@Repository
public interface CalendarEntityRepository extends JpaRepository<CalendarEntity, Long>{

	// 캘린더 insert
	long save(CalendarDTO dto);
	
	// 캘린더 select
	List<CalendarEntity> findAllByMember_mno(long userMno);


}
