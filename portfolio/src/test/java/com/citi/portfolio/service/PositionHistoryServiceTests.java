package com.citi.portfolio.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.PositionHistoryMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionHistoryServiceTests {

	@Autowired
	PositionHistoryMapper positionHistoryMapper;

	@Autowired
	PositionHistoryService positionHistoryService;

	@Test
	public void testSelectAllPositions() {
		Integer fundmanagerid=1;
		JSONArray jsonArray = positionHistoryService.selectAllPosition(fundmanagerid);
		Assert.assertFalse(jsonArray.isEmpty());
	}



}
