package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Portfolio;

import java.util.ArrayList;

public interface PortfolioMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolio record);

    int insertSelective(Portfolio record);

    Portfolio selectByPrimaryKey(Integer id);

    ArrayList<Portfolio> selectByfundManagerId(Integer fundManagerId);

    int updateByPrimaryKeySelective(Portfolio record);

    int updateByPrimaryKey(Portfolio record);
}