package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.*;
import com.citi.portfolio.entity.*;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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

    @Override
    public JSONObject deletePosition(Integer positionId) {
        JSONObject jsonObject = new JSONObject();
        int result = 0;
        Position position = positionMapper.selectByPrimaryKey(positionId);
        Portfolio portfolio = portfolioMapper.selectByPrimaryKey(position.getPortfolioid());

        if (insertPositionHistory(position, "SELL")) {
            result = positionMapper.deleteByPrimaryKey(positionId);
            if (result == 0) {
                positionHistoryMapper.deleteByPrimaryKey(positionId);
                jsonObject.put("errorMessage", "delete error");
            }else {
                portfolio.setSymbols(portfolio.getSymbols() -1);
                portfolio.setLotvalue(portfolio.getLotvalue() - position.getBenifit()-position.getLastprice()*position.getQuantity());
                portfolioMapper.updateByPrimaryKey(portfolio);
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
        logger.info(JSONObject.toJSON(positions));
        jsonArray = (JSONArray) JSONObject.toJSON(positions);
        return jsonArray;
    }

    @Override
    public JSONArray selectSymbol(String asset, String querysymbol) {
        JSONArray jsonArray = new JSONArray();
        if("Bond".equals(asset)) {
            ArrayList<Bond> bonds = bondMapper.selectBondBySymbol(querysymbol.toUpperCase().trim());
            if (!bonds.isEmpty()){
                jsonArray = (bonds.size()<9)? (JSONArray) JSONObject.toJSON(bonds):(JSONArray) JSONObject.toJSON(bonds.subList(0,9));
            }
        }
        if("Stock".equals(asset)){
            ArrayList<Stock> stocks = stockMapper.selectStockBySymbol(querysymbol.toUpperCase().trim());
            if (!stocks.isEmpty()){
                jsonArray = (stocks.size()<9)? (JSONArray) JSONObject.toJSON(stocks):(JSONArray) JSONObject.toJSON(stocks.subList(0,9));
            }
        }
        if ("Future".equals(asset)){
            ArrayList<Future> futures = futureMapper.selectFutureBySymbol(querysymbol.toUpperCase().trim());
            if (!futures.isEmpty()) {
                jsonArray = (futures.size()<9)? (JSONArray) JSONObject.toJSON(futures):(JSONArray) JSONObject.toJSON(futures.subList(0,9));
            }
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
        if (result == 1) {
            Position position = new Position();
            Calendar calendar = Calendar.getInstance();
            HashMap hashMap = new HashMap();
            hashMap.put("symbol",securityid);
            hashMap.put("date",calendar.getTime());
            position.setLastprice(priceMapper.selectBySymbolAndDate(hashMap).getBidprice());
            position.setCurrency(BASECURRENCY);
            position.setDatetime(calendar.getTime());
            position.setQuantity(quantity);
            position.setSecurityid(securityid);
            position.setAsset(asset);
            position.setBenifit(0d);
            position.setPortfolioid(portfolioid);
            if (positionMapper.insert(position) != 0) {
                Integer id = selectByPortfolioIdAndSecurityId(securityid, portfolioid);
                position.setId(id);
                if (id != -1) {
                    //add to position history
                    insertPositionHistory(position, "BUY");
                    jsonObject = (JSONObject) JSONObject.toJSON(position);
                    jsonObject.put("id", id);

                        Portfolio portfolio = portfolioMapper.selectByPrimaryKey(positionMapper.selectByPrimaryKey(id).getPortfolioid());
                        portfolio.setSymbols(portfolio.getSymbols() + 1);
                        portfolio.setLotvalue(portfolio.getLotvalue() + priceMapper.selectBySymbolAndDate(hashMap).getOfferprice());
                        portfolioMapper.updateByPrimaryKey(portfolio);

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
    public boolean insertPositionHistory(Position position, String buyOrSell) {
        Calendar calendar = Calendar.getInstance();

        PositionHistory positionHistory = new PositionHistory();
        positionHistory.setAsset(position.getAsset());
        positionHistory.setCurrency(position.getCurrency());
        positionHistory.setDatetime(calendar.getTime());
        if ("SELL".equals(buyOrSell.toUpperCase())){
            HashMap hashMap = new HashMap();
            hashMap.put("symbol",position.getSecurityid());
            hashMap.put("date",calendar.getTime());
            positionHistory.setLastprice(priceMapper.selectBySymbolAndDate(hashMap).getOfferprice());

        }else {
            positionHistory.setLastprice(position.getLastprice());
        }
        positionHistory.setPortfolioid(position.getPortfolioid());
        positionHistory.setQuantity(position.getQuantity());
        positionHistory.setSecurityid(position.getSecurityid());
        positionHistory.setBuyorsell(buyOrSell);
        if (positionHistoryMapper.insert(positionHistory) != 0){

            return true;
        }

        return false;
    }
}
