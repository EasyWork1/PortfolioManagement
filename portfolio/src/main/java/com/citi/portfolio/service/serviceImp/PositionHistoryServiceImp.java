package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionHistoryMapper;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.PositionHistory;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PositionHistoryServiceImp implements PositionHistoryService {
    @Autowired
    PositionHistoryMapper positionHistoryMapper;
    @Autowired
    PortfolioMapper portfolioMapper;


    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PositionHistoryServiceImp.class);

    @Override
    public JSONArray selectAllPosition(Integer fundmanagerid) {

       ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(fundmanagerid);
       ArrayList<PositionHistory> positionHistories = new ArrayList<>();
       JSONArray jsonArray = new JSONArray();
       if (!portfolios.isEmpty()){
           for (Portfolio p:portfolios
                ) {
               positionHistories.addAll(positionHistoryMapper.selectAllByPortfolioId(p.getId()));
           }
       }
       jsonArray = (JSONArray) JSONObject.toJSON(positionHistories);
       logger.info("selectAllPosition()---result: " + jsonArray);
       return  jsonArray;
    }
}
