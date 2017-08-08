package com.citi.portfolio.entity.DTO;

import java.util.Date;

public class FutureDTO {
    private String clralias;

    private String exchid;

    private String exch;

    private String sym;

    private String desc;

    private String sectyp;

    private Date matdt;

    private Double uomqty;


    private Date date;

    private Double bidprice;

    private Double offerprice;

    public FutureDTO(String clralias, String exchid, String exch, String sym, String desc, String sectyp, Date matdt, Double uomqty, Date date, Double bidprice, Double offerprice) {
        this.clralias = clralias;
        this.exchid = exchid;
        this.exch = exch;
        this.sym = sym;
        this.desc = desc;
        this.sectyp = sectyp;
        this.matdt = matdt;
        this.uomqty = uomqty;
        this.date = date;
        this.bidprice = bidprice;
        this.offerprice = offerprice;
    }

    public FutureDTO(String clralias, String exchid, String exch, String sym, String desc, String sectyp, Date matdt, Double uomqty) {
        this.clralias = clralias;
        this.exchid = exchid;
        this.exch = exch;
        this.sym = sym;
        this.desc = desc;
        this.sectyp = sectyp;
        this.matdt = matdt;
        this.uomqty = uomqty;
    }

    public FutureDTO() {
        super();
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

    public String getClralias() {
        return clralias;
    }

    public void setClralias(String clralias) {
        this.clralias = clralias == null ? null : clralias.trim();
    }

    public String getExchid() {
        return exchid;
    }

    public void setExchid(String exchid) {
        this.exchid = exchid == null ? null : exchid.trim();
    }

    public String getExch() {
        return exch;
    }

    public void setExch(String exch) {
        this.exch = exch == null ? null : exch.trim();
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym == null ? null : sym.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getSectyp() {
        return sectyp;
    }

    public void setSectyp(String sectyp) {
        this.sectyp = sectyp == null ? null : sectyp.trim();
    }

    public Date getMatdt() {
        return matdt;
    }

    public void setMatdt(Date matdt) {
        this.matdt = matdt;
    }

    public Double getUomqty() {
        return uomqty;
    }

    public void setUomqty(Double uomqty) {
        this.uomqty = uomqty;
    }
}