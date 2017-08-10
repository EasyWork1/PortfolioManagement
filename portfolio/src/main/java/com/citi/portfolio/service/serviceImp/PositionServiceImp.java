package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.*;
import com.citi.portfolio.entity.*;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.service.serviceInterface.PortfolioService;
import com.citi.portfolio.service.serviceInterface.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    FundManagerService fundManagerService;
    @Autowired
    PortfolioService portfolioService;

    static String testDate="2017-03-30";

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PortfolioServiceImp.class);

    static String BASECURRENCY = "USD";

    /**
     *
     * @param positionId
     * @return JSONObject of resultCode , if error occurs, add errorMessage information
     */
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
                fundManagerService.calculateBenifit(portfolio.getFundmanagerid());
                portfolio.setSymbols(portfolio.getSymbols() -1);
                portfolio.setLotvalue(portfolio.getLotvalue() - position.getBenifit()-position.getLastprice()*position.getQuantity());
                //portfolio.setBenefit(portfolioService.calculateLotvalue(portfolio.getId()) - portfolioService.getCost(portfolio.getId()));
                portfolioMapper.updateByPrimaryKey(portfolio);
            }
        } else {
            jsonObject.put("errorMessage", "delete error :: insert into history error");
        }
        jsonObject.put("resultCode", result);
        logger.info("insert position: Id=" + positionId + "~~~~~" + jsonObject);
        return jsonObject;
    }


    /**
     *
     * @param portfolioId
     * @return json of all the positions in the portfolio
     */
    @Override
    public JSONArray selectAllPosition(Integer portfolioId) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Position> positions = positionMapper.selectByPortfolioId(portfolioId);
        for (Position p:positions
             ) {
            HashMap hashMap = new HashMap();
            hashMap.put("symbol",p.getSecurityid());
            hashMap.put("date",testDate);
            Price price = priceMapper.selectBySymbolAndDate(hashMap);
            fundManagerService.calculateBenifit(portfolioMapper.selectByPrimaryKey(portfolioId).getFundmanagerid());
        }
        jsonArray = (JSONArray) JSONObject.toJSON(positions);
        logger.info("show all positions: " + jsonArray);
        return jsonArray;
    }

    /**
     *
     * @param asset
     * @param querysymbol
     * @return all the security included serarch words
     */
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
    public JSONArray selectSymbolData(String symbol) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Price> prices = priceMapper.selectBySymbol(symbol);
        ArrayList<Price> finalPrice =new ArrayList<>();
        try {
            for (Price p:prices
             ) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date date = format.parse(testDate);
                if (p.getDate().compareTo(date) != 1){
                    finalPrice.add(p);
                }

        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonArray = (JSONArray)JSONObject.toJSON(prices);
        logger.info("symbol data for " + symbol + " result :" + jsonArray);
        jsonArray = (JSONArray)JSONObject.toJSON(finalPrice);
        logger.info("symbol data for " + symbol + " result :" + jsonArray);
        return jsonArray;
//        ArrayList<String[]> data = new ArrayList<>();
//
//        for (Price p:prices){
//            SimpleDateFormat formatter;
//            formatter = new SimpleDateFormat ("yyyy-MM-dd");
//            String time = formatter.format(p.getDate()).replace("-",", ");
//            String date = "Date.UTC(" + time + ")";
//            String price=p.getOfferprice().toString();
//            String[] times = {date,price};
//            data.add(times);
//        }

    }

    @Override
    public JSONObject insertPosition(String securityid, String asset, Integer portfolioid,Double quantity) {
        JSONObject jsonObject = new JSONObject();
        int result = 1;

        //check the input
        if (securityid == null || asset == null || portfolioid == null || quantity == null){
            result = 0;
            jsonObject.put("errorMessage", "Input data error!");
        }
        //check whether the security exists in the portfolio
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

        //check the price exists
        HashMap hashMap = new HashMap();
        hashMap.put("symbol",securityid);
        hashMap.put("date",testDate);
        Price price = priceMapper.selectBySymbolAndDate(hashMap);
        if(price == null){
            result = 0;
            jsonObject.put("errorMessage", "not update price yet!");
        }

        if (result == 1) {
            Position position = new Position();

            //initialize the position
            position.setLastprice(price.getBidprice());
            position.setCurrency(BASECURRENCY);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(testDate);
                position.setDatetime(date);
                position.setQuantity(quantity);
                position.setSecurityid(securityid);
                position.setAsset(asset);
                position.setBenifit((price.getOfferprice() - price.getBidprice())*quantity);
                position.setPortfolioid(portfolioid);

                if (positionMapper.insert(position) != 0) { //insert success
                    Integer id = selectByPortfolioIdAndSecurityId(securityid, portfolioid);
                    position.setId(id);
                    //add to position history
                    if(!insertPositionHistory(position, "BUY")) {
                        positionMapper.deleteByPrimaryKey(id);
                        result = 0;
                        jsonObject.put("errorMessage", "insert into position history error");
                    }else {
                        jsonObject = (JSONObject) JSONObject.toJSON(position);
                        Portfolio portfolio = portfolioMapper.selectByPrimaryKey(portfolioid);
                        portfolio.setSymbols(portfolio.getSymbols() + 1);
                        portfolio.setLotvalue(portfolio.getLotvalue() + price.getOfferprice()*quantity);
                        portfolioMapper.updateByPrimaryKey(portfolio);
                        fundManagerService.calculateBenifit(portfolio.getFundmanagerid());
                    }
                } else {
                    jsonObject.put("errorMessage", "insert error");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        jsonObject.put("resultCode", result);
        logger.info("insert position: " + jsonObject);
        return jsonObject;
    }

    /**
     *
     * @param position
     * @param buyOrSell
     * @return whether success or fail
     * when add or delete position, add to position history
     */
    public boolean insertPositionHistory(Position position, String buyOrSell) {


        PositionHistory positionHistory = new PositionHistory();
        positionHistory.setAsset(position.getAsset());
        positionHistory.setCurrency(position.getCurrency());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(testDate);
            if ("SELL".equals(buyOrSell.toUpperCase())){
                HashMap hashMap = new HashMap();
                hashMap.put("symbol",position.getSecurityid());
                hashMap.put("date",testDate);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @param securityid
     * @param portfolioid
     * @return the id of position
     */
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

}
