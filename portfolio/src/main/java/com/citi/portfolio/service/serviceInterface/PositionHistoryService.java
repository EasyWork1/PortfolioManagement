package com.citi.portfolio.service.serviceInterface;


import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.Position;

import java.util.Date;

public interface PositionHistoryService {

    boolean insertPositionHistory(Position position,String buyOrSell);
}

