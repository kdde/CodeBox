package com.green.nowon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class GreengamesApplicationTests {

	@Autowired
	MemberEntityRepository repo;
	
	@Autowired
	private PasswordEncoder pe;
	
	
	//@Test
	void contextLoads() {
		repo.save(MemberEntity.builder()
				.mno(1)
				.name("name")
				.id("admiin")
				.pass(pe.encode("1234"))
				.phone("010")
				.build()
				.addRole(MyRole.ADMIN)
				);
		
	}

}
