package com.green.nowon.bus.dto;

import lombok.Data;

@Data
public class BusRouteItem {
	private String busRouteId;
	private String busRouteNm;
	private String busRouteAbrv;
	private String length;
	private String routeType;
	private String stStationNm;
	private String edStationNm;
	private String term;
	private String lastBusYn;
	private String lastBusTm;
	private String firstBusTm;
	private String lastLowTm;
	private String firstLowTm;
	private String corpNm;
}
