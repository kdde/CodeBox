package com.green.nowon.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.cate.DepartmentEntity;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findById(long id);

	MemberEntity findById(String id);
	
	Optional<MemberEntity> findByMno(long mno);

	Optional<MemberEntity> findAllById(long mno);

	Optional<DepartmentEntity> findAllByMno(long mno);

	Optional<MemberEntity> findAllById(String email);

	Optional<MemberEntity> findByName(String name);


}

