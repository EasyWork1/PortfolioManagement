package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Price;

public interface PriceMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(String symbol);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}