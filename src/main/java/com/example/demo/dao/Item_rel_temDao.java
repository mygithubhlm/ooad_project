package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Item_rel_tem;

import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
public interface Item_rel_temDao extends CrudRepository<Item_rel_tem,Integer>{
    public Item_rel_tem findByIditemAndIdtemplate(int iditem, int idtemplate);
    public List<Item_rel_tem> findByIditem(int iditem);
    public List<Item_rel_tem> findByIdtemplate(int idtemplate);

}
