package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionHistoryMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.dao.PriceMapper;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.PositionHistory;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import com.citi.portfolio.service.serviceInterface.PositionService;
import com.citi.portfolio.service.serviceInterface.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PositionHistoryServiceImp implements PositionHistoryService {
    @Autowired
    PriceMapper priceMapper;
    @Autowired
    PositionHistoryMapper positionHistoryMapper;


    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PositionHistoryServiceImp.class);

    @Override
    public boolean insertPositionHistory(Position position, String buyOrSell) {
        Calendar calendar = Calendar.getInstance();

        PositionHistory positionHistory = new PositionHistory();
        positionHistory.setAsset(position.getAsset());
        positionHistory.setId(position.getId());
        positionHistory.setCurrency(position.getCurrency());
        positionHistory.setDatetime(calendar.getTime());
        positionHistory.setLastprice(priceMapper.selectBySymbolAndDate(position.getSecurityid(),calendar.getTime()).getOfferprice());
        positionHistory.setPortfolioid(position.getPortfolioid());
        positionHistory.setQuantity(position.getQuantity());
        positionHistory.setSecurityid(position.getSecurityid());
        positionHistory.setBuyorsell(buyOrSell);
        if (positionHistoryMapper.insert(positionHistory) != 0){

            logger.info("insert sell position to positionHistory: PositionHistory{" +
                    "id=" + positionHistory.getId() +
                    ", securityid='" + positionHistory.getSecurityid() + '\'' +
                    ", asset='" + positionHistory.getAsset() + '\'' +
                    ", portfolioid=" + positionHistory.getPortfolioid() +
                    '}');
            return true;
        }
        logger.info("Fail insert sell position to positionHistory: PositionHistory{" +
                "id=" + positionHistory.getId() +
                ", securityid='" + positionHistory.getSecurityid() + '\'' +
                ", asset='" + positionHistory.getAsset() + '\'' +
                ", portfolioid=" + positionHistory.getPortfolioid() +
                '}');
            return false;
    }
}
