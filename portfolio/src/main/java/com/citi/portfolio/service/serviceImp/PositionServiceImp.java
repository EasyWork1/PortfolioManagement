package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PositionServiceImp implements PositionService {
    @Autowired
    PositionMapper positionMapper;

    @Override
    public ArrayList<Position> queryPosition(Integer portfolioId) {

        return null;
    }

    @Override
    public boolean deletePosition(Integer positionId) {
        return false;
    }

    @Override
    public boolean addPosition(Integer positionId) {
        return false;
    }
}
