package com.green.nowon.domain.dto.attendance;

import java.sql.Date;

import lombok.Data;

@Data
public class AttendanceSaveDTO {

	private String adtype;
	
	private Date date;
	
	private Date sdate;
	
	private Date edate;

	private String content;

	private long mno;
}
