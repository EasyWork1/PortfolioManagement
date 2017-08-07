package com.citi.portfolio.service.serviceInterface;


import com.citi.portfolio.entity.Position;

import java.util.ArrayList;

public interface PositionService {

    ArrayList<Position> queryPosition(Integer portfolioId);
    boolean deletePosition(Integer positionId);
    boolean addPosition(Integer positionId);
}
