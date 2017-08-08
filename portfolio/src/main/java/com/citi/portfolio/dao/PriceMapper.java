package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Price;

import java.util.Date;

public interface PriceMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(String symbol);

    Price selectBySymbolAndDate(String symbol,Date date);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}