package com.green.nowon.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository2;

import lombok.extern.log4j.Log4j2;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberEntityRepository2 repo;
	@Autowired
	private DepartmentMemberEntityRepository drepo;

	public MyUserDetailsService(MemberEntityRepository2 memberRepository) {
		this.repo = memberRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<DepartmentMemberEntity> aaa= drepo.findByMemberMno(repo.findById(username).get().getMno());
		
		Optional<MemberEntity> test = repo.findById(username);
		
		System.out.println(">>>>>>>>>>>>>>>"+test.get().getId());
		
		DepartmentMemberEntity aa= aaa.get(aaa.size()-1);
		
		return new MyUserDetails(
				repo.findById(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일")),
				aa
		);
	}

}
