package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.FundManager;

import java.util.ArrayList;

public interface BondMapper {
    int deleteByPrimaryKey(String isin);

    int insert(Bond record);

    int insertSelective(Bond record);

    ArrayList<BondDTO> selectBondDTO();

    ArrayList<Bond> selectBondBySymbol(String symbol);

    Bond selectByPrimaryKey(String isin);

    int updateByPrimaryKeySelective(Bond record);

    int updateByPrimaryKey(Bond record);
}