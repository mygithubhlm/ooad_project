package com.example.demo.service;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.dao.Item_rel_temDao;
import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.Check_item;
import com.example.demo.entity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lemonhuang on 2017/6/19.
 */
@Component
public class Check_itemService {
    @Autowired
    private Check_itemDao check_itemDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private Item_rel_temService item_rel_temService;

    //find a check_item
    public Check_item findCheck_item(String nameitem){
        System.out.println("AAAAAAAAAAAAAAAAAAA: "+nameitem);
        return check_itemDao.findByNameitem(nameitem);
    }

    //add a check_item
    public String addCheck_item(Check_item check_item){
        Check_item temp = findCheck_item(check_item.getName_item());
        if (temp==null) {
            check_itemDao.save(check_item);
            return "successful";
        }else
            return "fail";
    }

    //update the check_item
    public String updateCheck_item(Check_item check_item){
        Check_item temp = findCheck_item(check_item.getName_item());
        System.out.println("item_name: "+check_item.getName_item());

        if (temp==null)
            return "fail11";
        int id_item = check_item.getId_item();
        if(isPublish(id_item))
            return "fail22";
//        System.out.println("I'm executed");
        check_itemDao.save(check_item);
        return "successful";
    }

    //delete the check_item
    public String deleteCheck_item(String item_name){
        Check_item check_item = check_itemDao.findByNameitem(item_name);
        if (check_item==null)
            return "fail";
        int id_item = check_item.getId_item();
        if(isPublish(id_item))
            return "fail";
        check_itemDao.delete(check_item);
        return "successful";
    }

    //if the item is published
    private boolean isPublish(int id_item){
        List<Integer> id_temps = item_rel_temService.findTemByItem(id_item);
        for (int temp : id_temps){
            Template template = templateDao.findOne(temp);
            if (template.getStatustemplate()==1)
                return true;
        }
        return false;
    }


    public String addCheck_item(String name, String content){

        Check_item ci = new Check_item();
        ci.setName_item("name1");
        ci.setContent_item("con1");

        System.out.print("id_item: "+ci.getId_item());
        return addCheck_item(ci);
    }
}
