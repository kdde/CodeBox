package com.green.nowon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.cate.PositionRepository;
import com.green.nowon.service.PositionService;

@Service
public class PositionServiceProcess implements PositionService {

	@Autowired
	PositionRepository repo;
	
	@Override
	public void save(String name, int salary) {
		repo.save(PositionEntity.builder()
				.normalSalary(salary)
				.pName(name)
				.build());
	}

}
