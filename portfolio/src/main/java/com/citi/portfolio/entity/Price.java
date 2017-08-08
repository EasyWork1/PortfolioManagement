package com.citi.portfolio.entity;

import java.util.Date;

public class Price {
    private String symbol;

    private Double bidprice;

    private Double offerprice;

    private Date date;

    public Price(String symbol, Double bidprice, Double offerprice, Date date) {
        this.symbol = symbol;
        this.bidprice = bidprice;
        this.offerprice = offerprice;
        this.date = date;
    }

    public Price() {
        super();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public Double getBidprice() {
        return bidprice;
    }

    public void setBidprice(Double bidprice) {
        this.bidprice = bidprice;
    }

    public Double getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(Double offerprice) {
        this.offerprice = offerprice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}