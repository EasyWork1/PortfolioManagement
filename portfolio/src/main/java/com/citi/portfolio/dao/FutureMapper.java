package com.citi.portfolio.dao;

import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.DTO.FutureDTO;
import com.citi.portfolio.entity.Future;

import java.util.ArrayList;

public interface FutureMapper {
    int deleteByPrimaryKey(String clralias);

    int insert(Future record);

    int insertSelective(Future record);

    Future selectByPrimaryKey(String clralias);

    ArrayList<FutureDTO> selectFutureDTO();

    ArrayList<Future> selectFutureBySymbol(String symbol);

    int updateByPrimaryKeySelective(Future record);

    int updateByPrimaryKey(Future record);
}