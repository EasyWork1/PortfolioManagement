package com.citi.portfolio.service.serviceInterface;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface PositionHistoryService {


    JSONArray selectAllPosition(Integer fundmanagerid);

}

