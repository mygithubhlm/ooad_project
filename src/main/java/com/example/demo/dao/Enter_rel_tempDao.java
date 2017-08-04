package com.example.demo.dao;

import com.example.demo.entity.Enter_rel_temp;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lemonhuang on 2017/6/19.
 */
public interface Enter_rel_tempDao extends CrudRepository<Enter_rel_temp, Integer> {
    public Enter_rel_temp findByIditemreltemAndCodee(int iditemreltem, String codee);
}
