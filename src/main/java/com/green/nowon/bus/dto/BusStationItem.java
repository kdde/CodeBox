package com.green.nowon.bus.dto;

import lombok.Data;

@Data
public class BusStationItem {
		
	private String busRouteId;
	private String busRouteNm;
	private String busRouteAbrv;
	private String seq;//순번
	private String section;
	private String station;//정류소 고유ID
	private String arsId;//정류소 번호
	private String stationNm;//정류소 이름
	private String gpsX;
	private String gpsY;
	private String posX;
	private String posY;
	private String fullSectDist;
	private String direction;//진행방향
	private String stationNo;
	private String routeType;
	private String beginTm;
	private String lastTm;
	private String trnstnid;//회차지id
	private String sectSpd;
	private String transYn;//회차지여부

}
