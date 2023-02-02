package com.green.nowon.domain.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.green.nowon.domain.entity.BaseDateEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gen_seq_gg_profile",
sequenceName = "seq_gg_profile", initialValue = 1, allocationSize = 1)
@Table(name = "gg_profile")
@Entity
public class ProfileEntity extends BaseDateEntity{
	
	@Id
	@GeneratedValue(generator = "gen_seq_gg_profile", strategy = GenerationType.SEQUENCE)
	private long pno;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	
	@OnDelete(action= OnDeleteAction.CASCADE)
	@JoinColumn
	@ManyToOne
	private MemberEntity member;
}
