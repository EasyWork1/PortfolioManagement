package com.citi.portfolio;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.service.serviceImp.PortfolioServiceImp;
import com.citi.portfolio.service.serviceImp.PositionServiceImp;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioApplicationTests {
	@Autowired
	PositionService positionService;



	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Test.class);

	@Test
	public void contextLoads() {

		JSONArray bonds = positionService.selectSymbol("Bond","a");
		logger.info(bonds);

		Calendar calendar = Calendar.getInstance();
		logger.info(calendar.getTime());


	}
	@Test
	public void deletePosition() {

		JSONObject jsonObject = positionService.deletePosition(3);
		logger.info("deletePosition:" + jsonObject);



	}
	@Test
	public void insertPosition() {

		//JSONObject jsonObject = positionService.insertPosition("AAAP","Stock",1,100d);
		JSONObject jsonObject = positionService.insertPosition("AAAP","Stock",2,100d);
		logger.info("insertPosition:" + jsonObject);

	}

}
