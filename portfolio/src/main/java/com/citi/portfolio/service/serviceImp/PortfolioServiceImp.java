package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.service.serviceInterface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PortfolioServiceImp implements PortfolioService {
    @Autowired
    PortfolioMapper portfolioMapper;

    @Override
    public boolean insertPortfolio(Integer id, String name, Integer fundmanagerid) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(id);
        portfolio.setFundmanagerid(fundmanagerid);
        portfolio.setName(name);
        if (portfolioMapper.insert(portfolio) != 0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Portfolio> findPortfolioByFundManagerId(Integer fundManagerId) {
        ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(fundManagerId);
        return portfolios ;
    }
}
