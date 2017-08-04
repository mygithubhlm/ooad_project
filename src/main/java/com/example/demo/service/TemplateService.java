package com.example.demo.service;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.dao.Item_rel_temDao;
import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.Check_item;
import com.example.demo.entity.Item_rel_tem;
import com.example.demo.entity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
@Service
public class TemplateService {
    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private Item_rel_temDao item_rel_temDao;
    @Autowired
    private Check_itemDao check_itemDao;


    //no two templates will have the same name
    public String createTemplate(Template template){
        if (templateDao.findByNametemplate(template.getNametemplate())!=null)
            return "fail";
        templateDao.save(template);
        return "successful";
    }

    //fix the template
    public String updateTemplate(Template template){
        Template template1 = findTemplate(template.getIdtemplate());
        if (template1.getStatustemplate()==1)
            return "fail";
        if (!templateDao.exists(template.getIdtemplate()))
            return "fail";
        templateDao.save(template);
        return "successful";
    }

    //delete the template
    public String deleteTemplate(Template template){
        Template template1 = findTemplate(template.getIdtemplate());
        if (template1.getStatustemplate()==1)
            return "fail";
        if (!templateDao.exists(template.getIdtemplate()))
            return "fail";
        templateDao.delete(template);
        return "successful";
    }

    public Template findTemplate(String nametemplate){
        Template template = templateDao.findByNametemplate(nametemplate);
        return template;
    }

    public Template findTemplate(int idtemplate){
        return templateDao.findOne(idtemplate);
    }

    //show all the check items in a template
    public List<Check_item> showItemsInTem(Template template){
        List<Item_rel_tem> item_rel_tems =
                item_rel_temDao.findByIdtemplate(template.getIdtemplate());
        List<Check_item> result = new ArrayList<Check_item>();
        for (Item_rel_tem item_rel_tem:item_rel_tems
                ) {
            result.add(check_itemDao.findByiditem(item_rel_tem.getIditem()));
        }
        return result;
    }
//
//    public Template findById(int idtemplate){
//        Template template = templateDao.findOne(idtemplate);
//        return template;
//    }
}
