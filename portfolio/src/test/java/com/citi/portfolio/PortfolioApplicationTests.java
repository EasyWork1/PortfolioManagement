package com.citi.portfolio;

import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioApplicationTests {


	@Test
	public void contextLoads() {
	}

}
