package com.green.nowon.service.attendance;

import java.awt.print.Pageable;
import java.security.Principal;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.domain.dto.commuteMember.CommuteMemberListDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;

public interface CommuteService {

	Optional<CommuteEntity> findTodayTime(long mno);
	/**
	 * 가장 최근에 근무한 날짜 조회
	 * @return 
	 */
	Optional<CommuteEntity> findLastTime(long mno, CommuteInsertDTO idto);

	void save(long mno, CommuteInsertDTO idto, CommuteUpdateDTO udto);

	long MemberMno(Principal principal);

	

	

	/**
	 * ajax를 사용해서 저장된 시간을 보여주기
	 */
	void showListTime(long mno, Model model, int page);

}
