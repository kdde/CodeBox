package com.green.nowon.domain.entity.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository2 extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findById(String id);
	
	MemberEntity findByMno(long mno);
	
}
