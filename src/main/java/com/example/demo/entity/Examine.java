package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by think on 2017/6/19.
 */
@Entity
@Table(name="examine")
public class Examine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idexamine",nullable = false,length = 11)
    private int idexamine;

    @Column(name = "codee",nullable = false,length = 45)
    private String codee;

    @Column(name = "idtemplate",nullable = false,length = 11)
    private int idtemplate;

    @Column(name = "timebegin",nullable = false,length = 45)
    private String timebegin;

    @Column(name = "timeddl",nullable = false,length = 45)
    private String timeddl;

    @Column(name = "timecomp",nullable = true,length = 45)
    private String timecomp;

    @Column(name = "comonot",nullable = false,length = 11)
    private int comonot;

    public Examine(){
    }

    public Examine(String codee, int idtemplate, String timebegin, String timeddl){
        this.codee = codee;
        this.idtemplate = idtemplate;
        this.timebegin = timebegin;
        this.timeddl = timeddl;
        this.comonot = 0;
    }

    public int getIdexamine() {
        return idexamine;
    }

    public void setIdexamine(int idexamine) {
        this.idexamine = idexamine;
    }

    public String getCodee() {
        return codee;
    }

    public void setCodee(String codee) {
        this.codee = codee;
    }

    public int getIdtemplate() {
        return idtemplate;
    }

    public void setIdtemplate(int idtemplate) {
        this.idtemplate = idtemplate;
    }

    public String getTimebegin() {
        return timebegin;
    }

    public void setTimebegin(String timebegin) {
        this.timebegin = timebegin;
    }

    public String getTimeddl() {
        return timeddl;
    }

    public void setTimeddl(String timeddl) {
        this.timeddl = timeddl;
    }

    public String getTimecomp() {
        return timecomp;
    }

    public void setTimecomp(String timecomp) {
        this.timecomp = timecomp;
    }

    public int getComonot() {
        return comonot;
    }

    public void setComonot(int comonot) {
        this.comonot = comonot;
    }
}
