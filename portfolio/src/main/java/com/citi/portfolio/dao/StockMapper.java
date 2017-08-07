package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(String symbol);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}