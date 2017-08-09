package com.citi.portfolio.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PositionHistory {
    private Integer id;

    private Double lastprice;

    private Double quantity;

    private String currency;

    private String securityid;

    @JSONField(format="yyyy-MM-dd")
    private Date datetime;

    private String asset;

    private Integer portfolioid;

    private String buyorsell;

    public PositionHistory(Integer id, Double lastprice, Double quantity, String currency, String securityid, Date datetime, String asset, Integer portfolioid, String buyorsell) {
        this.id = id;
        this.lastprice = lastprice;
        this.quantity = quantity;
        this.currency = currency;
        this.securityid = securityid;
        this.datetime = datetime;
        this.asset = asset;
        this.portfolioid = portfolioid;
        this.buyorsell = buyorsell;
    }

    public PositionHistory() {
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

    public String getBuyorsell() {
        return buyorsell;
    }

    public void setBuyorsell(String buyorsell) {
        this.buyorsell = buyorsell == null ? null : buyorsell.trim();
    }

    @Override
    public String toString() {
        return "PositionHistory{" +
                "id=" + id +
                ", lastprice=" + lastprice +
                ", quantity=" + quantity +
                ", currency='" + currency + '\'' +
                ", securityid='" + securityid + '\'' +
                ", datetime=" + datetime +
                ", asset='" + asset + '\'' +
                ", portfolioid=" + portfolioid +
                ", buyorsell='" + buyorsell + '\'' +
                '}';
    }
}