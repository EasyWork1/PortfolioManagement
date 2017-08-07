package com.citi.portfolio.dao;

import com.citi.portfolio.entity.PositionHistory;

public interface PositionHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionHistory record);

    int insertSelective(PositionHistory record);

    PositionHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionHistory record);

    int updateByPrimaryKey(PositionHistory record);
}