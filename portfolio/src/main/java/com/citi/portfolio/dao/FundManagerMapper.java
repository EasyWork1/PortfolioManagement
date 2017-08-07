package com.citi.portfolio.dao;

import com.citi.portfolio.entity.FundManager;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface FundManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundManager record);

    int insertSelective(FundManager record);

    FundManager selectByPrimaryKey(Integer id);

    ArrayList<FundManager> selectByUserName(@Param("username")String username);

    ArrayList<FundManager> selectAll();

    int updateByPrimaryKeySelective(FundManager record);

    int updateByPrimaryKey(FundManager record);
}