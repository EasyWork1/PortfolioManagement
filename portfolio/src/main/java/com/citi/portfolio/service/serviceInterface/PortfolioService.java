package com.citi.portfolio.service.serviceInterface;


import com.citi.portfolio.entity.Portfolio;

import java.util.ArrayList;
import java.util.HashMap;

public interface PortfolioService {
    boolean insertPortfolio(Integer id, String name,Integer fundmanagerid);
    HashMap findPortfolioByFundManagerId(Integer fundManagerId);
    double calculateBenefit(Integer id);
}
