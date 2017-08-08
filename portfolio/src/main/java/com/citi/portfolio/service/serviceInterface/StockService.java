package com.citi.portfolio.service.serviceInterface;

import com.alibaba.fastjson.JSONObject;

public interface StockService {
    JSONObject selectAllStocks();
    JSONObject deleteStock(String id);
    JSONObject updateStock(String firstName, String lastName, String telephone, String email, String password);
}
