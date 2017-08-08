package com.citi.portfolio.service.serviceInterface;


import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.Portfolio;

import java.util.ArrayList;
import java.util.HashMap;

public interface PortfolioService {
    JSONObject insertPortfolio(String name,Integer fundmanagerid);
    JSONObject findPortfolioByFundManagerId(Integer fundManagerId);
    JSONObject calculateBenefit(Integer id);
}
