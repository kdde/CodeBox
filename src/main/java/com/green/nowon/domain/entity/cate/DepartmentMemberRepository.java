package com.green.nowon.domain.entity.cate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMemberRepository extends JpaRepository<DepartmentMemberEntity, Long> {

	List<DepartmentMemberEntity> findAllByDepartment_dno(Long dno);


}
