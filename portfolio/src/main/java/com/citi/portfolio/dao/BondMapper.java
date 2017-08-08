package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.FundManager;

import java.util.ArrayList;

public interface BondMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bond record);

    int insertSelective(Bond record);

    ArrayList<Bond> selectAll();

    Bond selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bond record);

    int updateByPrimaryKey(Bond record);
}