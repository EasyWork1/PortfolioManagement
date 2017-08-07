package com.citi.portfolio.entity;

import java.util.Date;

public class Position {
    private Integer id;

    private Double offerprice;

    private Double bidprice;

    private Double quantity;

    private String currency;

    private String securityid;

    private Date datetime;

    private String asset;

    public Position(Integer id, Double offerprice, Double bidprice, Double quantity, String currency, String securityid, Date datetime, String asset) {
        this.id = id;
        this.offerprice = offerprice;
        this.bidprice = bidprice;
        this.quantity = quantity;
        this.currency = currency;
        this.securityid = securityid;
        this.datetime = datetime;
        this.asset = asset;
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

    public Double getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(Double offerprice) {
        this.offerprice = offerprice;
    }

    public Double getBidprice() {
        return bidprice;
    }

    public void setBidprice(Double bidprice) {
        this.bidprice = bidprice;
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
}