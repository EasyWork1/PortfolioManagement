package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PositionServiceImp implements PositionService {
    @Autowired
    PositionMapper positionMapper;

    @Override
    public JSONObject deletePosition(Integer positionId) {
        JSONObject jsonObject = new JSONObject();
        int result= positionMapper.deleteByPrimaryKey(positionId);
        jsonObject =(JSONObject) jsonObject.put("resultCode",result);
        return jsonObject;
    }
//
//    @Override
//    public JSONObject insertPosition(Double lastprice, Double quantity, String currency, String securityid, Date datetime, String asset, Integer portfolioid) {
//        return null;
//    }


//return    public JSONObject addPosition(Integer positionId,Integer portfolioId) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("resultCode",0);
//
//        if (positionMapper.selectByPortfolioId()selectByName(name).equals(null)){
//            Portfolio portfolio = new Portfolio();
//            portfolio.setFundmanagerid(fundmanagerid);
//            portfolio.setName(name);
//            portfolio.setLotvalue(0d);
//            portfolio.setSymbols(0);
//            portfolio.setBenefit(0d);
//            if (portfolioMapper.insert(portfolio) != 0){
//                jsonObject = (JSONObject)JSONObject.toJSON(portfolio);
//                jsonObject.put("resultCode",1);
//            }else {
//                jsonObject.put("errorMessage", "insert error");
//            }
//
//        }else{
//            jsonObject.put("errorMessage", "The portfolio name has already exist!");
//        }
//        return  jsonObject;
//    }
}
