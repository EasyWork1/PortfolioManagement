package com.citi.portfolio.entity;

public class Portfolio {
    private Integer id;

    private String name;

    private Integer fundmanagerid;

    private Double benefit;

    public Portfolio(Integer id, String name, Integer fundmanagerid, Double benefit) {
        this.id = id;
        this.name = name;
        this.fundmanagerid = fundmanagerid;
        this.benefit = benefit;
    }

    public Portfolio() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFundmanagerid() {
        return fundmanagerid;
    }

    public void setFundmanagerid(Integer fundmanagerid) {
        this.fundmanagerid = fundmanagerid;
    }

    public Double getBenefit() {
        return benefit;
    }

    public void setBenefit(Double benefit) {
        this.benefit = benefit;
    }
}