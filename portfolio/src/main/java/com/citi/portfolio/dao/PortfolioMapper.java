package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Portfolio;

public interface PortfolioMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolio record);

    int insertSelective(Portfolio record);

    Portfolio selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Portfolio record);

    int updateByPrimaryKey(Portfolio record);
}