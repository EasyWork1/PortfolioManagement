package com.citi.portfolio.entity;

public class Bond {
    private Integer id;

    private String issuer;

    private Double coupon;

    private String maturitymonth;

    private Integer maturityyear;

    public Bond(Integer id, String issuer, Double coupon, String maturitymonth, Integer maturityyear) {
        this.id = id;
        this.issuer = issuer;
        this.coupon = coupon;
        this.maturitymonth = maturitymonth;
        this.maturityyear = maturityyear;
    }

    public Bond() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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