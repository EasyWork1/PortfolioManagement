package com.citi.portfolio.service.serviceInterface;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.Position;

import java.util.ArrayList;
import java.util.Date;

public interface PositionService {

    JSONObject deletePosition(Integer positionId);
    JSONObject insertPosition(String securityid, String asset, Integer portfolioid,Double quantity) ;
    JSONArray selectAllPosition(Integer portfolioId);
    JSONArray selectSymbol(String asset, String querysymbol);
    JSONArray selectSymbolData(Integer portfolioid);

}

