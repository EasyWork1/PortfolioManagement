package com.citi.portfolio.service.serviceInterface;


import com.citi.portfolio.entity.Portfolio;

import java.util.ArrayList;

public interface PortfolioService {
    boolean insertPortfolio(Integer id, String name,Integer fundmanagerid);
    ArrayList<Portfolio> findPortfolioByFundManagerId(Integer fundManagerId);

}
