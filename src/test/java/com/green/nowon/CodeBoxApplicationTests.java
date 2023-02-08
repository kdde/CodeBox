package com.green.nowon;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.AddressEntity;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class CodeBoxApplicationTests {

	@Autowired
	MemberEntityRepository repo;
	
	@Autowired
	AddressEntityRepsoitory addressrepo;
	
	@Autowired
	DepartmentMemberEntityRepository dmRepo;
	
	@Autowired
	private DepartmentEntityRepository departmentRepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	//주의사항 -> DB의 모든 PK가 1이여야합니다.따라사 기존에 생성 하는 도중에 실수를
	//했을 경우 DB부터 처음부터 만들어 주세요
	//id : admin 
	//pass: 1234
	//문제 발생시 직접 DB에서 수정하셔도 됩니다.
	@Test//어드민 생성
	void contextLoads() {
		//멤버 생성
		repo.save(MemberEntity.builder()
				.mno(1)
				.name("관리자")
				.id("admin")
				.email("admin@greengames.shop")
				.pass(pe.encode("1234"))
				.phone("010-1234-5678")
				.hireDate(LocalDate.now())
				.build()
				.addRole(MyRole.ADMIN)
				);
		//멤버의 주소 설정
		addressrepo.save(AddressEntity.builder()
				.postcode("01849")
				.roadAddress("서울 노원구 공릉로 95")
				.jibunAddress("서울 노원구 공릉동 661-11")
				.detailAddress("노원그린아카데미")
				.extraAddress("(공릉동)")
				.member(repo.findById("admin"))
				.build());
		//부서 설정 -> 회사명만 만들기 임시 관리자가 부서를 생성해 주어야 합니다.
		departmentRepo.save(DepartmentEntity.builder().dname("코드박스").depth(1).parent(null).build());
		//관리자 소속을 기본 회사 소속으로 설정해줍니다.
		dmRepo.save(DepartmentMemberEntity.builder() 
				  .department(DepartmentEntity.builder() .dno(1) .build())
				  .member(MemberEntity.builder() .mno(1) .build()) .build());
		
	}

}
