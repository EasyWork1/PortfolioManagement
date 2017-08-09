package com.citi.portfolio;

import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin.javascript.JSObject;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundManagerTests {

	@Autowired
	BondMapper bondMapper;

	@Autowired
	FundManagerService fundManagerService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSelectAllBonds(){
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		ArrayList<BondDTO> bonds = bondMapper.selectBondDTO(sqlDate);
		Assert.assertFalse(bonds.isEmpty());
		System.out.print(bonds.size());
	}
	@Test
	public void testCalculate(){
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		JSONObject jsonObject = fundManagerService.calculateBenifit(17);
		Assert.assertEquals((Integer)jsonObject.get("resultCode"),new Integer(1));
	}

}
