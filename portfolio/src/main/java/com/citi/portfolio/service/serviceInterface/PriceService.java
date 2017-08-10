package com.citi.portfolio.service.serviceInterface;

import com.alibaba.fastjson.JSONArray;
import com.citi.portfolio.entity.Price;

import java.util.ArrayList;
import java.util.Date;

public interface PriceService {
    Double getOfferPriceBySymbol(String symbol,Date date);
}
