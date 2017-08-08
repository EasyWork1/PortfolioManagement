package com.citi.portfolio.entity;

public class Bond {
    private String isin;

    private String issuer;

    private Double coupon;

    private String maturitymonth;

    private Integer maturityyear;

    public Bond(String isin, String issuer, Double coupon, String maturitymonth, Integer maturityyear) {
        this.isin = isin;
        this.issuer = issuer;
        this.coupon = coupon;
        this.maturitymonth = maturitymonth;
        this.maturityyear = maturityyear;
    }

    public Bond() {
        super();
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin == null ? null : isin.trim();
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