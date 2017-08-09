package com.citi.portfolio.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Position {
    private Integer id;

    private Double lastprice;

    private Double quantity;

    private String currency;

    private String securityid;

    @JSONField(format="yyyy-MM-dd")
    private Date datetime;

    private String asset;

    private Integer portfolioid;

    private Double benifit;

    public Position(Integer id, Double lastprice, Double quantity, String currency, String securityid, Date datetime, String asset, Integer portfolioid, Double benifit) {
        this.id = id;
        this.lastprice = lastprice;
        this.quantity = quantity;
        this.currency = currency;
        this.securityid = securityid;
        this.datetime = datetime;
        this.asset = asset;
        this.portfolioid = portfolioid;
        this.benifit = benifit;
    }

    public Position() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLastprice() {
        return lastprice;
    }

    public void setLastprice(Double lastprice) {
        this.lastprice = lastprice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getSecurityid() {
        return securityid;
    }

    public void setSecurityid(String securityid) {
        this.securityid = securityid == null ? null : securityid.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset == null ? null : asset.trim();
    }

    public Integer getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(Integer portfolioid) {
        this.portfolioid = portfolioid;
    }

    public Double getBenifit() {
        return benifit;
    }

    public void setBenifit(Double benifit) {
        this.benifit = benifit;
    }
}