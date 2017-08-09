package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Price;

import java.util.Date;

public interface PriceMapper {


    int insert(Price record);

    int insertSelective(Price record);

    Price selectBySymbolAndDate(String symbol,Date date);


}