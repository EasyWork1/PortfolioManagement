package com.citi.portfolio.service.serviceInterface;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.Position;

import java.util.ArrayList;

public interface PositionService {

    JSONObject deletePosition(Integer positionId);
//    JSONObject insertPosition(Double lastprice,Double quantity,String currency,String securityid,Date datetime,String asset, Integer portfolioid);
}

