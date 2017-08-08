package com.citi.portfolio.service.serviceInterface;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

public interface PriceService {
    Double getOfferPriceBySymbol(String symbol,Date date);
}
