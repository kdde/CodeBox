package com.green.nowon.naver;

import java.util.List;

import lombok.Data;

@Data
public class OrgResponse {
	private List<OrgUnit> orgUnits;
	private ResponseMetaData responseMetaData;

}
