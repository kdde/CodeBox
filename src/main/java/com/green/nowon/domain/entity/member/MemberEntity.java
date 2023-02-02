package com.green.nowon.domain.entity.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;
import com.green.nowon.domain.entity.BaseDateEntity;
import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author LeeYongJu
 * 직원 관련 DB
 * DB 이름 : GgMember
 * 컬럼 목록 : mno(사번) ,name(이름), id(아이디) , pass(비밀번호) , phone(번호)  
 * , mem_img(프로필)
 * myRole : user(사원) , admin(팀장)
 */
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "seq_gen_GgMember", 
		sequenceName = "seq_GgMember", initialValue = 1, allocationSize = 1)
@Table(name = "GgMember")
@Entity
public class MemberEntity extends BaseDateEntity{
	
	@GeneratedValue(generator = "seq_gen_GgMember", strategy = GenerationType.SEQUENCE)
	@Id
	private long mno;//사원번호
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String id;//이메일x ->수정 id로 변경 (domain은 모두 같은 부분을 사용하기 때문에)
	
	@Column(nullable = false)
	private String pass;//비밀번호
	
	@Column(nullable = false)
	private String phone;//번호

	@Column(nullable = true)
	private LocalDate hireDate;
	
	@Column(nullable = true)
	@ColumnDefault("0")
	private double boList;//보너스
	
	@Column(nullable = true)
	@ColumnDefault("0")
	private Integer totSalary;//tot = nomalsal + bonus-min
	
	@Column(nullable = false)
	private String email;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private PositionEntity pno;//position 으로 바꾸길 추천
	
	//@Builder.Default
	//@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToOne(mappedBy="member", optional=true)
	//List<ProfileEntity> profile=new ArrayList<>();
	ProfileEntity profile;
	
	@Builder.Default
	@CollectionTable(name = "GgDeploy")
	@Enumerated(EnumType.STRING)//직책
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roles = new HashSet<>();

	public MemberEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	
	public MemberEntity update(MemberUpdateDTO dto) {
		this.phone = dto.getPhone();
		return this;
	}
//	/**
//	 * 대표이미지 없는데 없으면 @builder가 안먹힘
//	 * @return
//   * optional=false를 사용해서 강제로 하나의 데이터만 가져와서 사용
//	 */ 
//	public ProfileEntity defImg() {
//		for(ProfileEntity pimg:profile) {
//			return pimg;
//		}
//		return profile;
//	}
	
}