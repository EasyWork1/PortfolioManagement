package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Position;

import java.util.ArrayList;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    ArrayList<Position> selectByPortfolioId(Integer portfolioid);


    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}