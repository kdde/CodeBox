package com.green.nowon.service;

import java.security.Principal;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;

public interface MemberService {
	void save(MemberInsertDTO mdto,AddressInsertDTO adto);
	void detail(long mno, Model model) ;
	Map<String, String> fileTempUpload(MultipartFile img);
	void update(long mno, MemberUpdateDTO dto);
	void list(Model model);
}
