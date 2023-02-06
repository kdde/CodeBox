package com.green.nowon.bus.dto;

import lombok.Data;

@Data
public class ComMsgHeader {
	
	private String responseMsgID;
	private String responseTime;
	private String requestMsgID;
	private String returnCode;
	private String successYN;
	private String errMsg;
}          
