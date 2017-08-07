package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Bond;

public interface BondMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bond record);

    int insertSelective(Bond record);

    Bond selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bond record);

    int updateByPrimaryKey(Bond record);
}