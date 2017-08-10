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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class StockServiceImp implements StockService {

    @Autowired
    StockMapper stockMapper;


    @Override
    public JSONObject selectAllStocks() {
        JSONObject json = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(simpleDateFormat.parse("2017-03-30").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<StockDTO> stocks = stockMapper.selectStockDTO(sqlDate);
        JSONArray StockJson = (JSONArray) JSONObject.toJSON(stocks);
        json.put("total",stocks.size());
        json.put("data",StockJson);
        return json;
    }

}
