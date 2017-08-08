package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.controller.FundManagerController;
import com.citi.portfolio.dao.*;
import com.citi.portfolio.entity.MyPortfolio;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.Stock;
import com.citi.portfolio.service.serviceInterface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class PortfolioServiceImp implements PortfolioService {
    @Autowired
    PortfolioMapper portfolioMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    PriceMapper priceMapper;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PortfolioServiceImp.class);

    /**
     * @author keira
     * @param name
     * @param fundmanagerid
     * @return the jsonobject of insert portfolio
     */
    @Override
    public JSONObject insertPortfolio(String name, Integer fundmanagerid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode",0);
        
        //check the name is available
        if (portfolioMapper.selectByName(name) != null){
            jsonObject.put("errorMessage", "The portfolio name has already exist!");
        }else{
            Portfolio portfolio = new Portfolio();
            portfolio.setFundmanagerid(fundmanagerid);
            portfolio.setName(name);
            portfolio.setLotvalue(0d);
            portfolio.setSymbols(0);
            portfolio.setBenefit(0d);
            if (portfolioMapper.insert(portfolio) != 0){
                jsonObject = (JSONObject)JSONObject.toJSON(portfolio);
                jsonObject.put("id",portfolioMapper.selectByName(name).getId());
                jsonObject.put("resultCode",1);
            }else {
                jsonObject.put("errorMessage", "insert error");
            }
        }
            return  jsonObject;
    }

    @Override
    public JSONArray findPortfolioByFundManagerId(Integer fundManagerid) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(fundManagerid);

        if (!portfolios.isEmpty()){
            for (Portfolio p:portfolios
                    ) {
                p.setBenefit(calculateLotvalue(p.getId()) - getCost(p.getId()));
                p.setSymbols(portfolioMapper.getCurrentSymbolsByPortfolioId(p.getId()));
                p.setLotvalue(calculateLotvalue(p.getId()));
            }
        }
        jsonArray = (JSONArray) JSONObject.toJSON(portfolios);
        return jsonArray;

    }

    @Override
    public JSONObject deletePortfolio(Integer id) {
        JSONObject jsonObject = new JSONObject();
        int result = portfolioMapper.deleteByPrimaryKey(id);
        jsonObject.put("resultCode",result);
        logger.info("delete portfolio: " + id );
        return jsonObject;
    }

    public double calculateLotvalue(Integer id) {
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(id);
        double benefitSum = 0d;
        Calendar calendar = Calendar.getInstance();
        for (Position p :positions) {
            benefitSum += priceMapper.selectBySymbolAndDate(p.getSecurityid(),calendar.getTime()).getOfferprice() * p.getQuantity();
        }
        return benefitSum;
    }
    public double getCost(Integer id) {
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(id);
        double sumCost = 0d;
        for (Position p :positions ) {
            sumCost += p.getLastprice() * p.getQuantity();
        }
        return sumCost;
    }

}
