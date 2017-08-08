package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONObject;
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
    public JSONObject insertPortfolio(String name, Integer fundmanagerid) {
            JSONObject jsonObject = new JSONObject();
         jsonObject.put("resultCode",0);

        if (portfolioMapper.selectByName(name).equals(null)){
            Portfolio portfolio = new Portfolio();
            portfolio.setFundmanagerid(fundmanagerid);
            portfolio.setName(name);
            if (portfolioMapper.insert(portfolio) != 0){
                jsonObject = (JSONObject)JSONObject.toJSON(portfolio);
                jsonObject.put("resultCode",1);
            }else {
                jsonObject.put("errorMessage", "insert error");
            }

        }else{
            jsonObject.put("errorMessage", "The portfolio name has already exist!");
        }
            return  jsonObject;

    }

    @Override
    public JSONObject findPortfolioByFundManagerId(Integer fundManagerid) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(fundManagerid);
        jsonObject = (JSONObject) JSONObject.toJSON(portfolios);
        return jsonObject;

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
