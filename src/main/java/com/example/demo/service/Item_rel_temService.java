package com.example.demo.service;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.dao.Item_rel_temDao;
import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.Check_item;
import com.example.demo.entity.Item_rel_tem;
import com.example.demo.entity.Template;
import com.example.demo.exception.CantFindTableException;
import com.example.demo.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
@Service
public class Item_rel_temService {
    @Autowired
    private Item_rel_temDao item_rel_temDao;
    @Autowired
    private Check_itemDao check_itemDao;
    @Autowired
    private TemplateDao templateDao;

    public Item_rel_tem findItem_rel_tem(int iditem,int idtemplate){
        if (!templateDao.exists(idtemplate))
            throw new NoSuchEntityException(this.getClass().getName(),
                    Template.class);
        if (!check_itemDao.exists(iditem))
            throw new NoSuchEntityException(this.getClass().getName(),
                    Check_item.class);
        Item_rel_tem item_rel_tem = item_rel_temDao.findByIditemAndIdtemplate
                (iditem,idtemplate);
        return item_rel_tem;
    }

    public Item_rel_tem findItem_rel_tem(int iditemreltem){
        return item_rel_temDao.findOne(iditemreltem);
    }

    public String deleteItem_rel_tem(Item_rel_tem item_rel_tem){
        Item_rel_tem item_rel_tem1 = findItem_rel_tem(item_rel_tem.getIditemreltem());
        if (item_rel_tem1==null) {
            throw new CantFindTableException(this.getClass().getName()
                    , Item_rel_tem.class);
        }
        item_rel_temDao.delete(item_rel_tem);
        return "successful";
    }

    //return the list of templates' id
    public List<Integer> findTemByItem(int iditem){
        List<Integer> temId = new ArrayList<Integer>();
        List<Item_rel_tem> temp = item_rel_temDao.findByIditem(iditem);
        for (Item_rel_tem item_rel_tem: temp
             ) {
            temId.add(item_rel_tem.getIdtemplate());
        }
        return temId;
    }

    public List<Item_rel_tem> findByTemplate(Template template){
        if (!templateDao.exists(template.getIdtemplate()))
            throw new NoSuchEntityException(this.getClass().getName(),
                    Template.class);
        return item_rel_temDao.findByIdtemplate(template.getIdtemplate());
    }


    public String addItem_rel_tem(Item_rel_tem item_rel_tem){
        Item_rel_tem item_rel_tem1 = findItem_rel_tem(item_rel_tem.getIditemreltem());
        Item_rel_tem item_rel_tem2 = findItem_rel_tem(item_rel_tem.getIditem(),item_rel_tem.getIdtemplate());
        if (item_rel_tem1!=null||item_rel_tem2!=null)
            return "fail";
        item_rel_temDao.save(item_rel_tem);
        return "successful";
    }

}
