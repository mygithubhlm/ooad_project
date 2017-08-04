package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by think on 2017/6/19.
 */
@Entity
@Table(name = "item_rel_tem")
public class Item_rel_tem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iditemreltem",nullable = false,length = 11)
    private int iditemreltem;

    @Column(name = "iditem",nullable = false,length = 45)
    private int  iditem;

    @Column(name = "idtemplate",nullable = false,length = 45)
    private int idtemplate;

    public  Item_rel_tem(){
    }
    public Item_rel_tem(int iditem, int iditemplate){
        this.iditem = iditem;
        this.idtemplate = iditemplate;
    }

    public int getIditemreltem() {
        return iditemreltem;
    }

    public void setIditemreltem(int iditemreltem) {
        this.iditemreltem = iditemreltem;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public int getIdtemplate() {
        return idtemplate;
    }

    public void setIdtemplate(int idtemplate) {
        this.idtemplate = idtemplate;
    }
}
