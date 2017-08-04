package com.example.demo.dao;

import com.example.demo.entity.Check_item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lemonhuang on 2017/6/18.
 */
@Repository
public interface Check_itemDao extends CrudRepository<Check_item, Integer> {
    public Check_item findByiditem(int id_item);
    public Check_item findByNameitem(String nameitem);

}
