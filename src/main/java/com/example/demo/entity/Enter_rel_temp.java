package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by lemonhuang on 2017/6/19.
 */
@Entity
@Table(name = "enter_rel_temp")
public class Enter_rel_temp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identerreltemp")
    private int identerreltemp;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iditemreltem", nullable = false, length = 11)
    private int iditemreltem;

    @Column(name = "codee", nullable = false, length = 45)
    private String codee;

    @Column(name = "endonot", nullable = false, length = 11)
    private int endonot;


    public Enter_rel_temp(){}
    public Enter_rel_temp(int iditemreltem, String codee, int endOnot){
        this.iditemreltem = iditemreltem;
        this.codee = codee;
        this.endonot = endOnot;

    }
    public int getIdenterreltemp() {
        return identerreltemp;
    }

    public void setIdenterreltemp(int identerreltemp) {
        this.identerreltemp = identerreltemp;
    }

    public int getIditemreltem() {
        return iditemreltem;
    }

    public void setIditemreltem(int iditemreltem) {
        this.iditemreltem = iditemreltem;
    }

    public String getCodee() {
        return codee;
    }

    public void setCodee(String codee) {
        this.codee = codee;
    }

    public int getEndOnot() {
        return endonot;
    }

    public void setEndOnot(int endOnot) {
        this.endonot = endOnot;
    }
}
