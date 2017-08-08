package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.StockMapper;
import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.Stock;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StockServiceImp implements StockService {

    @Autowired
    StockMapper stockMapper;


    @Override
    public JSONObject selectAllStocks() {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Stock> stocks = stockMapper.selectAll();
        jsonObject = (JSONObject) JSONObject.toJSON(stocks);
        return jsonObject;
    }

    @Override
    public JSONObject deleteStock(String id) {
        JSONObject jsonObject = new JSONObject();
        int result= stockMapper.deleteByPrimaryKey(id);
        jsonObject =(JSONObject) jsonObject.put("resultCode",result);
        return jsonObject;
    }

    @Override
    public JSONObject updateStock(String firstName, String lastName, String telephone, String email, String password) {
        return null;
    }
}
