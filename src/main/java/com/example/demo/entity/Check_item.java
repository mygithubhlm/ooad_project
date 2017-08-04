package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by lemonhuang on 2017/6/18.
 */
@Entity
@Table(name = "check_item")
public class Check_item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iditem")
    private int iditem;

    @Column(name = "nameitem", nullable = false, length = 45)
    private String nameitem;

    @Column(name = "contentitem", nullable = false, length = 45)
    private String content_item;

    public Check_item(){}
    public Check_item(String name_item, String content_item){
        this.nameitem = name_item;
        this.content_item = content_item;
    }
    public int getId_item() {
        return iditem;
    }

    public void setId_item(int id_item) {
        this.iditem = id_item;
    }

    public String getName_item() {
        return nameitem;
    }

    public void setName_item(String name_item) {
        this.nameitem = name_item;
    }

    public String getContent_item() {
        return content_item;
    }

    public void setContent_item(String content_item) {
        this.content_item = content_item;
    }

    @Override
    public String toString(){
        return nameitem+", "+content_item;
    }
}
