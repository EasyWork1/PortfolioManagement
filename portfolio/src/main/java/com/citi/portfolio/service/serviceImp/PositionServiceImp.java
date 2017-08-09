package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.*;
import com.citi.portfolio.entity.*;
import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

@Service
public class PositionServiceImp implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    PositionHistoryMapper positionHistoryMapper;
    @Autowired
    PortfolioMapper portfolioMapper;
    @Autowired
    PriceMapper priceMapper;
    @Autowired
    BondMapper bondMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    FutureMapper futureMapper;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PortfolioServiceImp.class);

    static String BASECURRENCY = "USD";

    PositionHistoryService positionHistoryService;

    @Override
    public JSONObject deletePosition(Integer positionId) {
        JSONObject jsonObject = new JSONObject();
        int result = 0;
        if (positionHistoryService.insertPositionHistory(positionMapper.selectByPrimaryKey(positionId), "sell")) {
            result = positionMapper.deleteByPrimaryKey(positionId);
            if (result == 0) {
                positionHistoryMapper.deleteByPrimaryKey(positionId);
                jsonObject.put("errorMessage", "delete error");
            }
        } else {
            jsonObject.put("errorMessage", "delete error :: insert into history error");
        }
        jsonObject.put("resultCode", result);
        logger.info("insert position: Id=" + positionId);
        return jsonObject;
    }

    @Override
    public Integer selectByPortfolioIdAndSecurityId(String securityid, Integer portfolioid) {
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(portfolioid);
        Integer id = -1;
        for (Position p : positions
                ) {
            if (securityid.equals(p.getSecurityid())) {
                id = p.getId();
            }
        }
        return id;
    }

    @Override
    public JSONArray selectAllPosition(Integer portfolioId) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(portfolioId);
        jsonArray = (JSONArray) JSONObject.toJSON(positions);
        return jsonArray;
    }

    @Override
    public JSONArray selectSymbol(String asset, String querysymbol) {
        JSONArray jsonArray = new JSONArray();
        if("Bond".equals(asset)) {
            ArrayList<Bond> bonds = bondMapper.selectBondBySymbol(querysymbol.toUpperCase().trim());
            logger.info(bonds.get(0).getIsin());
            jsonArray = (JSONArray) JSONObject.toJSON(bonds.subList(0,9));
        }
        if("Stock".equals(asset)){
            ArrayList<Stock> stocks = stockMapper.selectStockBySymbol(querysymbol.toUpperCase().trim());
            jsonArray = (JSONArray) JSONObject.toJSON(stocks.subList(0,9));
        }
        if ("Future".equals(asset)){
            ArrayList<Future> futures = futureMapper.selectFutureBySymbol(querysymbol.toUpperCase().trim());
            jsonArray = (JSONArray) JSONObject.toJSON(futures.subList(0,9));
        }

        return  jsonArray;

    }

    @Override
    public JSONObject insertPosition(String securityid, String asset, Integer portfolioid,Double quantity) {
        JSONObject jsonObject = new JSONObject();
        int result = 1;
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(portfolioid);
        if (!positions.isEmpty()) {
            for (Position p : positions
                    ) {
                if (p.getSecurityid().equals(securityid)) {
                    result = 0;
                    jsonObject.put("errorMessage", "Already exists!");
                }
            }
        }
        if (result != 1) {
            Position position = new Position();
            Calendar calendar = Calendar.getInstance();
            position.setLastprice(priceMapper.selectBySymbolAndDate(securityid,new Date()).getBidprice());
            position.setCurrency(BASECURRENCY);
            position.setDatetime(calendar.getTime());
            position.setQuantity(quantity);
            position.setSecurityid(securityid);
            position.setAsset(asset);
            position.setBenifit(0d);
            position.setPortfolioid(portfolioid);
            if (positionMapper.insert(position) != 0) {
                Integer id = selectByPortfolioIdAndSecurityId(securityid, portfolioid);
                if (id != -1) {
                    //add to position history
                    positionHistoryService.insertPositionHistory(positionMapper.selectByPrimaryKey(id), "buy");
                    jsonObject = (JSONObject) JSONObject.toJSON(position);
                    jsonObject.put("id", id);
                }else{
                    result=0;
                    jsonObject.put("errorMessage", "insert error");
                }
            }else {
                jsonObject.put("errorMessage", "insert error");
            }

        }
        jsonObject.put("resultCode", result);
        logger.info("insert position: " + jsonObject);
        return jsonObject;
    }
}
