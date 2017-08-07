package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.dao.StockMapper;
import com.citi.portfolio.entity.MyPortfolio;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.Stock;
import com.citi.portfolio.service.serviceInterface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Service
public class PortfolioServiceImp implements PortfolioService {
    @Autowired
    PortfolioMapper portfolioMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    FundManagerMapper fundManagerMapper;

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
    public HashMap findPortfolioByFundManagerId(Integer fundManagerid) {

        HashMap hashMap = new HashMap();
        ArrayList<MyPortfolio> portfolios= portfolioMapper.showMyPofolio(fundManagerid);;

            hashMap.put("result",true);
            hashMap.put("portfolios",portfolios);
            hashMap.put("fundmanager",fundManagerMapper.selectByPrimaryKey(fundManagerid));

        return hashMap;
    }

    @Override
    public double calculateBenefit(Integer id) {
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(id);
        double benefitSum = 0d;
        double sumCost = 0d;
        for (Position p :positions ) {
            benefitSum += getCurrentPrice(p.getSecurityid(),p.getAsset()) * p.getQuantity();
            sumCost += p.getLastprice() * p.getQuantity();
        }
        return benefitSum/sumCost;
    }

    private double getCurrentPrice(String securityid, String asset) {
        Stock stock;
        // other security
        if (!Objects.equals("Stock", asset)) {
            return 0;
        } else {
            stock = stockMapper.selectByPrimaryKey(securityid);
            return stock.getLastsale();
        }
    }
}
