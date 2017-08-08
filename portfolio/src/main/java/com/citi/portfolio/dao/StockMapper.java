package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.DTO.StockDTO;
import com.citi.portfolio.entity.Stock;

import java.util.ArrayList;

public interface StockMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(Stock record);

    int insertSelective(Stock record);
    ArrayList<StockDTO> selectStockDTO();

    Stock selectByPrimaryKey(String symbol);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}