package com.green.nowon.naver.movie;

import java.util.List;

import lombok.Data;

@Data
public class NaverMovieResponse {
	
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<Item> items;
}
