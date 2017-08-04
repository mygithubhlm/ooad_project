package com.example.demo.test;

import com.example.demo.entity.Check_item;
import com.example.demo.entity.Item_rel_tem;
import com.example.demo.entity.Template;
import com.example.demo.service.Check_itemService;
import com.example.demo.service.Item_rel_temService;
import com.example.demo.service.TemplateService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lemonhuang on 2017/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Item_rel_temTests {

    @Autowired
    Item_rel_temService item_rel_temService;

    @Autowired
    Check_itemService check_itemService;

    @Autowired
    TemplateService templateService;


    private Check_item check_item;
    private Check_item check_item1;

    private Template template;
    private Template template1;

    private Item_rel_tem item_rel_tem;
    private Item_rel_tem item_rel_tem1;
    private Item_rel_tem item_rel_tem2;

    @Before
    public void beforeItem_rel_tem(){
        check_item = new Check_item("item11","content1");
        check_item1 = new Check_item("item22","content2");

        template = new Template("thisTemplate21","just for test1",0);
        template1 = new Template("thisTemplate22","just for test2",0);

        check_itemService.addCheck_item(check_item);
        check_item = check_itemService.findCheck_item("item11");

        check_itemService.addCheck_item(check_item1);
        check_item1 = check_itemService.findCheck_item("item22");

        templateService.createTemplate(template);
        template = templateService.findTemplate("thisTemplate21");

        templateService.createTemplate(template1);
        template1 = templateService.findTemplate("thisTemplate22");


        item_rel_tem = new Item_rel_tem(check_item.getId_item(),template.getIdtemplate());
        item_rel_tem1 = new Item_rel_tem(check_item1.getId_item(), template.getIdtemplate());
        item_rel_tem2 = new Item_rel_tem(check_item.getId_item(), template1.getIdtemplate());

    }

    @Test
    public void item_rel_tem(){

        //add new item_rel_tems to database
        item_rel_temService.addItem_rel_tem(item_rel_tem);
        Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(item_rel_tem.getIditem(),item_rel_tem.getIdtemplate()));

        item_rel_temService.addItem_rel_tem(item_rel_tem1);
        Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(check_item1.getId_item(),template.getIdtemplate()));

        item_rel_temService.addItem_rel_tem(item_rel_tem2);
        Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(check_item1.getId_item(),template.getIdtemplate()));

        //delete a template, and the relative item_rel_tem will be all deleted
        templateService.deleteTemplate(template1);
        Assert.assertEquals(null, item_rel_temService.findItem_rel_tem(item_rel_tem2.getIditemreltem()));

        //delete a check_item, and the relative item_rel_tem will be all deleted
        check_itemService.deleteCheck_item(check_item.getName_item());
        Assert.assertEquals(null, item_rel_temService.findItem_rel_tem(item_rel_tem.getIditemreltem()));

    }
    @After
    public void endWork(){
        check_itemService.deleteCheck_item(check_item1.getName_item());
        templateService.deleteTemplate(template);
    }
}
