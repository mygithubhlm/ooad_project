package com.example.demo.service;

import com.example.demo.dao.Enter_rel_tempDao;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.Item_rel_temDao;
import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.Enter_rel_temp;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Item_rel_tem;
import com.example.demo.entity.Template;
import com.example.demo.exception.CantFindTableException;
import com.example.demo.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemonhuang on 2017/6/19.
 */
@Component
public class Enter_rel_tempService {
    @Autowired
    private Enter_rel_tempDao enter_rel_tempDao;
    @Autowired
    private Item_rel_temDao item_rel_temDao;
    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private EnterpriseDao enterpriseDao;

    public String addEnter_rel_temp(Enter_rel_temp enter_rel_temp){
        Enter_rel_temp temp = findEnter_rel_temp(enter_rel_temp.getIdenterreltemp());
        if (temp==null){
            enter_rel_tempDao.save(enter_rel_temp);
            return "successful";
        }else
            return "fail";

    }

    public Enter_rel_temp findEnter_rel_temp(int id){
        return enter_rel_tempDao.findOne(id);
    }

    public Enter_rel_temp findEnter_rel_temp(int iditemreltem, String codee){
        if (!enterpriseDao.exists(codee))
            throw new NoSuchEntityException(this.getClass().getName(),
                    Enterprise.class);
        if (!item_rel_temDao.exists(iditemreltem))
            throw new NoSuchEntityException(this.getClass().getName(),
                    Item_rel_tem.class);
        Enter_rel_temp result = enter_rel_tempDao.findByIditemreltemAndCodee(
                iditemreltem, codee);
        if (result==null)
            throw new CantFindTableException(this.getClass().getName(),
                    Enter_rel_temp.class);
        return result;
    }

    //find this relationship by template and enterprise
    public List<Enter_rel_temp> findTheseByTemAndEnter(
            Template template,Enterprise enterprise){
        if (!templateDao.exists(template.getIdtemplate()))
            throw new NoSuchEntityException(
                    this.getClass().getName(),Template.class);
        if (!enterpriseDao.exists(enterprise.getCodee()))
            throw new NoSuchEntityException(
                    this.getClass().getName(),Enterprise.class
            );
        List<Item_rel_tem> item_rel_tems =
                item_rel_temDao.findByIdtemplate(template.getIdtemplate());
        List<Enter_rel_temp> result = new ArrayList<Enter_rel_temp>();
        for (Item_rel_tem item_r_tem:item_rel_tems
             ) {
            if(enter_rel_tempDao.findByIditemreltemAndCodee(
                    item_r_tem.getIditemreltem(),enterprise.getCodee())==null)
                continue;
//            System.out.println("hi hi "+enter_rel_tempDao.findByIditemreltemAndCodee(item_r_tem.getIditemreltem(),enterprise.getCodee()));
            result.add(enter_rel_tempDao.findByIditemreltemAndCodee(
                    item_r_tem.getIditemreltem(),enterprise.getCodee()));
        }
        return result;
    }
}
