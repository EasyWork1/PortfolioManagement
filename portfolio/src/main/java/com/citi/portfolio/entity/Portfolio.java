package com.citi.portfolio.entity;

public class Portfolio {
    private Integer id;

    private String name;

    private Integer fundmanagerid;

    private Double benefit;

    private Integer symbols;

    private Double lotvalue;

    public Portfolio(Integer id, String name, Integer fundmanagerid, Double benefit, Integer symbols, Double lotvalue) {
        this.id = id;
        this.name = name;
        this.fundmanagerid = fundmanagerid;
        this.benefit = benefit;
        this.symbols = symbols;
        this.lotvalue = lotvalue;
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

    public Integer getSymbols() {
        return symbols;
    }

    public void setSymbols(Integer symbols) {
        this.symbols = symbols;
    }

    public Double getLotvalue() {
        return lotvalue;
    }

    public void setLotvalue(Double lotvalue) {
        this.lotvalue = lotvalue;
    }
}