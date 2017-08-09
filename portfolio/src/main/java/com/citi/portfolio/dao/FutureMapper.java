package com.citi.portfolio.dao;

import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.DTO.FutureDTO;
import com.citi.portfolio.entity.Future;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

public interface FutureMapper {
    int deleteByPrimaryKey(String clralias);

    int insert(Future record);

    int insertSelective(Future record);

    Future selectByPrimaryKey(String clralias);

    ArrayList<FutureDTO> selectFutureDTO(Date date);

    ArrayList<Future> selectFutureBySymbol(@Param("querysymbol")String querysymbol);

    int updateByPrimaryKeySelective(Future record);

    int updateByPrimaryKey(Future record);
}