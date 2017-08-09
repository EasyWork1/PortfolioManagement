package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.StockMapper;
import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.DTO.StockDTO;
import com.citi.portfolio.entity.Stock;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class StockServiceImp implements StockService {

    @Autowired
    StockMapper stockMapper;


    @Override
    public JSONArray selectAllStocks() {
        JSONArray json = new JSONArray();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ArrayList<StockDTO> stocks = stockMapper.selectStockDTO(sqlDate);
        json = (JSONArray) JSONObject.toJSON(stocks);
        return json;
    }

}
