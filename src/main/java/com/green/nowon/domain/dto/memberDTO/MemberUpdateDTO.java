package com.green.nowon.domain.dto.memberDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.ProfileEntity;
import com.green.nowon.util.MyFileUtils;

import lombok.Data;

@Data
public class MemberUpdateDTO {

	private String pass;
	private String phone;
	
	private String[] newName;
	private String[] orgName;
	
	public List<ProfileEntity> toItemListImgs(MemberEntity entity, String url) {
		List<ProfileEntity> imgs = new ArrayList<>();
		for (int i = 0; i < orgName.length; i++) {
			if(orgName[i].equals("") || orgName[i]==null)continue;
			boolean def = false;
			if(i==0)def=true;
			ProfileEntity profile = ProfileEntity.builder()
					.url(url)
					.orgName(orgName[i])
					.newName(newName[i])
					.member(entity)
					.build();
			imgs.add(profile);
		}
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
	}
	
	public MemberEntity updateEntity() {
		return MemberEntity.builder()
					.pass(pass)//비밀번호 암호화
					.phone(phone)
				.build();
	}
}
