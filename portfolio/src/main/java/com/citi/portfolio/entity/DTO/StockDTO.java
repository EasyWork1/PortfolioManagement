package com.citi.portfolio.entity.DTO;

import java.util.Date;


public class StockDTO {
    private String symbol;

    private String name;

    private Double lastsale;

    private Long marketcap;

    private Integer ipoyear;

    private String sector;

    private String industry;

    private Date date;

    private String currency;
    private Double bidprice;

    private Double offerprice;

    public StockDTO(String symbol, String name, Double lastsale, Long marketcap, Integer ipoyear, String sector, String industry, Date date, String currency) {
        this.symbol = symbol;
        this.name = name;
        this.lastsale = lastsale;
        this.marketcap = marketcap;
        this.ipoyear = ipoyear;
        this.sector = sector;
        this.industry = industry;
        this.date = date;
        this.currency = currency;
    }

    public StockDTO(String symbol, String name, Double lastsale, Long marketcap, Integer ipoyear, String sector, String industry, Date date, String currency, Double bidprice, Double offerprice) {
        this.symbol = symbol;
        this.name = name;
        this.lastsale = lastsale;
        this.marketcap = marketcap;
        this.ipoyear = ipoyear;
        this.sector = sector;
        this.industry = industry;
        this.date = date;
        this.currency = currency;
        this.bidprice = bidprice;
        this.offerprice = offerprice;
    }

    public StockDTO() {
        super();
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLastsale() {
        return lastsale;
    }

    public void setLastsale(Double lastsale) {
        this.lastsale = lastsale;
    }

    public Long getMarketcap() {
        return marketcap;
    }

    public void setMarketcap(Long marketcap) {
        this.marketcap = marketcap;
    }

    public Integer getIpoyear() {
        return ipoyear;
    }

    public void setIpoyear(Integer ipoyear) {
        this.ipoyear = ipoyear;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector == null ? null : sector.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }
}