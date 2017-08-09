package com.citi.portfolio.dao;

import com.citi.portfolio.entity.Bond;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.FundManager;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.sql.Date;

public interface BondMapper {
    int deleteByPrimaryKey(String isin);

    int insert(Bond record);

    int insertSelective(Bond record);

    ArrayList<BondDTO> selectBondDTO(Date date);

    ArrayList<Bond> selectBondBySymbol(@Param("querysymbol")String querysymbol);

    Bond selectByPrimaryKey(String isin);

    int updateByPrimaryKeySelective(Bond record);

    int updateByPrimaryKey(Bond record);
}