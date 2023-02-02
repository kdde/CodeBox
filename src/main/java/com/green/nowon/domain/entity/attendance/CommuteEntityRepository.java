package com.green.nowon.domain.entity.attendance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteEntityRepository extends JpaRepository<CommuteEntity, Long>{
	void save(long mno);

	List<CommuteEntity> findAllByMember_mno(long mno);

	Object findAllByCno(long cno);

	Page<CommuteEntity> findAllByMember_mno(long mno, Pageable pageable);
	
}
