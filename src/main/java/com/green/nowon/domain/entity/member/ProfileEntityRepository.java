package com.green.nowon.domain.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEntityRepository extends JpaRepository<ProfileEntity, Long>{

	void deleteByMember_mno(long mno);
	
	

}
