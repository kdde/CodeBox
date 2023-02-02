package com.green.nowon.domain.entity.cate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.dto.memberDTO.DepartmentDTO;

@Repository
public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long>{

	Optional<DepartmentEntity> findByParentDnoAndDname(Long parentDno, String dName);

	Optional<DepartmentEntity> findByParentDnoNullAndDname(String dName);

	Optional<DepartmentEntity> findByParentDnoOrderByDnameAsc(Long parentDno);


	List<DepartmentEntity> findAllByParentDno(Long parentDno);

	List<DepartmentEntity> findAllByDepth(int i);
	
}
