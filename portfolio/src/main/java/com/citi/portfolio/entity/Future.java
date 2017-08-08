package com.citi.portfolio.entity;

import java.util.Date;

public class Future {
    private String clralias;

    private String exchid;

    private String exch;

    private String sym;

    private String desce;

    private String sectyp;

    private Date matdt;

    private Double uomqty;

    public Future(String clralias, String exchid, String exch, String sym, String desce, String sectyp, Date matdt, Double uomqty) {
        this.clralias = clralias;
        this.exchid = exchid;
        this.exch = exch;
        this.sym = sym;
        this.desce = desce;
        this.sectyp = sectyp;
        this.matdt = matdt;
        this.uomqty = uomqty;
    }

    public Future() {
        super();
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

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce == null ? null : desce.trim();
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