package com.example.demo.dao;

import com.example.demo.entity.Template;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by think on 2017/6/19.
 */
public interface TemplateDao extends CrudRepository<Template,Integer>{
    public Template findByNametemplate(String nametemplate);
}
