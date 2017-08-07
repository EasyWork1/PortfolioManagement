package com.citi.portfolio.entity;

/**
 * Created by keira on 2017/8/7.
 */
public class MyPortfolio {

    private String name;

    private int symbols;

    private double lotvalue;

    private Double benefit;

    private Double benefitPer;

    public Double getBenefitPer() {
        return benefitPer;
    }

    public void setBenefitPer(Double benefitPer) {
        this.benefitPer = benefitPer;
    }


    public MyPortfolio(String name, int symbols, double lotvalue, Double benefit) {
        this.name = name;
        this.symbols = symbols;
        this.lotvalue = lotvalue;
        this.benefit = benefit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSymbols() {
        return symbols;
    }

    public void setSymbols(int symbols) {
        this.symbols = symbols;
    }

    public double getLotvalue() {
        return lotvalue;
    }

    public void setLotvalue(double lotvalue) {
        this.lotvalue = lotvalue;
    }

    public Double getBenefit() {
        return benefit;
    }

    public void setBenefit(Double benefit) {
        this.benefit = benefit;
    }
}
