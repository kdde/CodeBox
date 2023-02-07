package com.green.nowon.movie;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeResult {
	
	private String boxofficeType; //박스오피스 종류
	private String showRange; //박스오피스 조회 일자
	private List<Item> dailyBoxOfficeList;
}
