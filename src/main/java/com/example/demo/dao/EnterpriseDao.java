package com.example.demo.dao;

import com.example.demo.entity.Enterprise;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lemonhuang on 2017/6/19.
 */
public interface EnterpriseDao extends CrudRepository<Enterprise, String>{
    public Enterprise findByNamee(String namee);
    public Enterprise findByCodee(String codee);

}
