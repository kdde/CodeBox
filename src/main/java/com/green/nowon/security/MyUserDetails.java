package com.green.nowon.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.ProfileEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private long mno;
	private boolean admin;

	private Set<MyRole> roles;
	private DepartmentEntity department;
	private String dName;

	private ProfileEntity profile;
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	public MyUserDetails(MemberEntity memEnt,DepartmentMemberEntity depMemEnt) {
		this(memEnt.getId(), memEnt.getPass(), memEnt.getRoles() //set<MyRole> ---> set<GrantedAuthority>
				.stream()
				.map(Role->new SimpleGrantedAuthority(Role.getRole()) ) //Stream<GrantedAuthority> "ROLE_USER" or "ROLE_ADMIN"
				.collect(Collectors.toSet()));
		
		this.id=memEnt.getId();
		this.name=memEnt.getName();
		this.mno=memEnt.getMno();

		this.roles = memEnt.getRoles();

		this.dName=depMemEnt.getDepartment().getDname();

		
		for(MyRole role:memEnt.getRoles()) {
			if(role.name().equals("ADMIN")) {
				admin=true;
			}
		}
		this.department=depMemEnt.getDepartment();
		this.profile = memEnt.getProfile();
	}
	

}
