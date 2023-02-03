package com.green.nowon.bus.dto;

import java.util.List;

import lombok.Data;

@Data
public class MsgBody {
	List<BusRouteItem> itemList;
}
