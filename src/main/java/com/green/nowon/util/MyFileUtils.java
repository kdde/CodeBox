package com.green.nowon.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

//수정(업데이트 시) 필요
public class MyFileUtils {

	public static void moveUploadLocationFromTemp(String[] newName, String url) {
		ClassPathResource cpr = new ClassPathResource("static"+url+"temp/");
		
		for(String name:newName) {
			try {
				File file = new File(cpr.getFile(), name);
				file.renameTo(new File(cpr.getFile().getParent(), name));
			} catch (Exception e) {
				
			}
		}
	}

	public static Map<String, String> fileUpload(MultipartFile bimg, String location) {
		ClassPathResource cpr = new ClassPathResource("static"+location);
		File folder = null;
		String fileName = null;
		String orgName = null;
		try {
			folder = cpr.getFile();
			orgName = bimg.getOriginalFilename();
			
			int idx = orgName.lastIndexOf(".");//파일 이름중에서 마지막(.)의 인덱스 번호
			fileName = orgName.substring(0, idx)
					+"_"+(System.nanoTime()/1000000)
					+orgName.substring(idx);// .+확장자
			
			bimg.transferTo(new File(folder, fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
		Map<String, String> tempfile = new HashMap<>();
		tempfile.put("newName", fileName);
		tempfile.put("orgName", orgName);
		tempfile.put("url", location+fileName);
		return tempfile;
	}
	
	private MyFileUtils() {}

	}

