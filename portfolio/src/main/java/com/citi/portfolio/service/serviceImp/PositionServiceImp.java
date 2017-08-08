package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionHistoryMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.PositionHistory;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PositionServiceImp implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    PositionHistoryMapper positionHistoryMapper;

    PositionHistoryService positionHistoryService;

    @Override
    public JSONObject deletePosition(Integer positionId) {
        JSONObject jsonObject = new JSONObject();
        int result = 0;
        if (positionHistoryService.insertPositionHistory(positionMapper.selectByPrimaryKey(positionId),"sell")) {
            result = positionMapper.deleteByPrimaryKey(positionId);
            if (result == 0){
                positionHistoryMapper.deleteByPrimaryKey(positionId);
                jsonObject.put("errorMessage","delete error");
            }
        }
        else {
            jsonObject.put("errorMessage","delete error :: insert into history error");
        }
        jsonObject.put("resultCode", result);

        return jsonObject;
    }
//
//    @Override
//    public JSONObject insertPosition(Double lastprice, Double quantity, String currency, String securityid, Date datetime, String asset, Integer portfolioid) {
//        JSONObject jsonObject = new JSONObject();
//        Position position = new Position();
//
//        //int result= positionMapper.insert();
//        jsonObject =(JSONObject) jsonObject.put("resultCode",result);
//        return jsonObject;
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
