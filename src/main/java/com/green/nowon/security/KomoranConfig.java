package com.green.nowon.security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;

@Configuration
public class KomoranConfig {
	//private String DEPT_DIC="dept.dic";
	//private String DIC_DIR="static/files/";
	private String USER_DIC="user.dic";
	@Autowired
	DepartmentEntityRepository dept;
	
	@Autowired
	MemberEntityRepository member;
	
		
	@Bean
	Komoran komoran() {
		userDic();
		
		Komoran komoran=new Komoran(DEFAULT_MODEL.LIGHT);
		komoran.setUserDic(USER_DIC);
		
		return komoran;
	}

	//부서테이블(부서명), 멤버테이블(이름) 
	private void userDic() {
		////////////////////////////////////////////////////
		//ClassPathResource cpr=new ClassPathResource(DIC_DIR);
		Set<String> keys = new HashSet<>();
		
		//기존에 수동으로 등록된 파일에서 고유명사만 추출
		try {
			// FileReader fr=new FileReader(new File(cpr.getFile(), USER_DIC));
			File file=new File(USER_DIC);
				if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String data = null;
				while ((data = br.readLine()) != null) {
					if (data.startsWith("#"))//주석라인제거
						continue;
					String[] str = data.split("\\t");
					keys.add(str[0]);
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		////////////////////////////////////////////////////
		//부서명을 set에 저장
		dept.findAll().forEach(e -> {
			keys.add(e.getDname());
		});
		//사원명을 set에 저장
		member.findAll().forEach(e -> {
			keys.add(e.getName());
		});
		
		//저장된 명단을 고유명사로 파일에 등록
		////////////////////////////////////////////////////
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(USER_DIC));
			keys.forEach(key -> {
				try {
					bw.write(key + "\tNNP\n");
					System.out.println(key);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////////
	}
	
	
	
	
}
