package com.example.demo.entity;

import com.sun.tools.javac.comp.Enter;

import javax.persistence.*;

/**
 * Created by lemonhuang on 2017/6/19.
 */
@Entity
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @Column(name = "codee", nullable = false, length = 45)
    private String codee;

    @Column(name = "namee", nullable = false, length = 45)
    private String namee;

    @Column(name = "statuse", nullable = false, length = 45)
    private String statuse;

    @Column(name = "codeorg", nullable = false, length = 45)
    private String codeorg;

    @Column(name = "typetrade", nullable = true, length = 45)
    private String typetrade;

    @Column(name = "trade", nullable = true, length = 45)
    private String trade;

    @Column(name = "catemanage", nullable = true, length = 45)
    private String catemanage;

    @Column(name = "linkman", nullable = true, length = 45)
    private String linkman;

    @Column(name = "tellinkman", nullable = true, length = 45)
    private String tellinkman;

    public Enterprise(){
    }
    public Enterprise(String codee, String namee, String statuse, String codeorg, String typetrade, String trade, String catemanage, String linkman, String tellinkman){
        this.codee = codee;
        this.namee = namee;
        this.statuse = statuse;
        this.codeorg = codeorg;
        this.typetrade = typetrade;
        this.trade = trade;
        this.catemanage = catemanage;
        this.linkman = linkman;
        this.tellinkman = tellinkman;
    }
    public String getCodee() {
        return codee;
    }

    public void setCodee(String codee) {
        this.codee = codee;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getStatuse() {
        return statuse;
    }

    public void setStatuse(String statuse) {
        this.statuse = statuse;
    }

    public String getCodeorg() {
        return codeorg;
    }

    public void setCodeorg(String codeorg) {
        this.codeorg = codeorg;
    }

    public String getTypetrade() {
        return typetrade;
    }

    public void setTypetrade(String typetrade) {
        this.typetrade = typetrade;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getCatemanage() {
        return catemanage;
    }

    public void setCatemanage(String catemanage) {
        this.catemanage = catemanage;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTellinkman() {
        return tellinkman;
    }

    public void setTellinkman(String tellinkman) {
        this.tellinkman = tellinkman;
    }
}
