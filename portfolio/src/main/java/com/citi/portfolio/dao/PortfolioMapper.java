package com.citi.portfolio.dao;

import com.citi.portfolio.entity.MyPortfolio;
import com.citi.portfolio.entity.Portfolio;

import java.util.ArrayList;
import java.util.List;

public interface PortfolioMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolio record);

    int insertSelective(Portfolio record);

    Portfolio selectByPrimaryKey(Integer id);

    Portfolio selectByName(String name);

    ArrayList<Portfolio> selectByfundManagerId(Integer fundmanagerid);

    int getCurrentSymbolsByPortfolioId(Integer portfolioid);

    int updateByPrimaryKeySelective(Portfolio record);

    int updateByPrimaryKey(Portfolio record);


}