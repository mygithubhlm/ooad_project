package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by think on 2017/6/19.
 */
@Entity
@Table(name = "template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtemplate",nullable = false,length = 11)
    private int idtemplate;

    @Column(name = "nametemplate",nullable = false,length = 45)
    private String nametemplate;

    @Column(name = "desctemplate",nullable = false,length = 45)
    private String desctemplate;

    @Column(name = "statustemplate",nullable = false,length = 11)
    private int statustemplate;

    @ManyToMany
    private Set<Check_item>  items;

    public Template(){
    }

    public Template(String nametemplate, String desctemplate, int statustemplate){
        this.nametemplate = nametemplate;
        this.desctemplate = desctemplate;
        this.statustemplate = statustemplate;
    }

    public int getStatustemplate() {
        return statustemplate;
    }

    public void setStatustemplate(int statustemplate) {
        this.statustemplate = statustemplate;
    }

    public int getIdtemplate() {
        return idtemplate;
    }

    public String getNametemplate() {
        return nametemplate;
    }

    public String getDesctemplate() {
        return desctemplate;
    }

    public void setIdtemplate(int idtemplate) {
        this.idtemplate = idtemplate;
    }

    public void setNametemplate(String nametemplate) {
        this.nametemplate = nametemplate;
    }

    public void setDesctemplate(String desctemplate) {
        this.desctemplate = desctemplate;
    }
}
