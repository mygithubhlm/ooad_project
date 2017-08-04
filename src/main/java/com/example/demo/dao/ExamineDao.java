package com.example.demo.dao;

import com.example.demo.entity.Examine;
import org.springframework.data.repository.CrudRepository;

import javax.crypto.ExemptionMechanism;
import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
public interface ExamineDao extends CrudRepository<Examine,Integer>{
    public Examine findByCodeeAndIdtemplate(String codee, int idtemplate);
    public List<Examine> findByIdtemplate(int idtemplate);
    public List<Examine> findByCodee(String codee);
}
