package com.citi.portfolio.entity.DTO;

import java.util.Date;

public class BondDTO {
    private String isin;

    private String issuer;

    private Double coupon;

    private String maturitymonth;

    private Integer maturityyear;

    private Date date;

    private Double bidprice;

    private Double offerprice;

    public BondDTO(String isin, String issuer, Double coupon, String maturitymonth, Integer maturityyear, Date date, Double bidprice, Double offerprice) {
        this.isin = isin;
        this.issuer = issuer;
        this.coupon = coupon;
        this.maturitymonth = maturitymonth;
        this.maturityyear = maturityyear;
        this.date = date;
        this.bidprice = bidprice;
        this.offerprice = offerprice;
    }

    public BondDTO(String isin, String issuer, Double coupon, String maturitymonth, Integer maturityyear) {
        this.isin = isin;
        this.issuer = issuer;
        this.coupon = coupon;
        this.maturitymonth = maturitymonth;
        this.maturityyear = maturityyear;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public BondDTO() {
        super();
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public String getMaturitymonth() {
        return maturitymonth;
    }

    public void setMaturitymonth(String maturitymonth) {
        this.maturitymonth = maturitymonth == null ? null : maturitymonth.trim();
    }

    public Integer getMaturityyear() {
        return maturityyear;
    }

    public void setMaturityyear(Integer maturityyear) {
        this.maturityyear = maturityyear;
    }
}