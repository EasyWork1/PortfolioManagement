package com.citi.portfolio.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.PositionHistoryMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionServiceTests {
	@Autowired
	PositionMapper positionMapper;

	@Autowired
	PositionService positionService;
	@Test
	public void testSelectSymbolData() {
		String symbol="AAMC";
		JSONArray jsonArray = positionService.selectSymbolData(symbol);
		Assert.assertFalse(jsonArray.isEmpty());
	}
	@Test
	public void testInsertPosition() {
		JSONObject jsonObject = positionService.insertPosition("JOB","Stock",1,1000d);
		Assert.assertFalse(jsonObject.isEmpty());
	}



}
