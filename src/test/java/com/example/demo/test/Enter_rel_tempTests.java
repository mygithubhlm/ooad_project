package com.example.demo.test;

import com.example.demo.entity.*;
import com.example.demo.service.*;
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
public class Enter_rel_tempTests {
    @Autowired
    private Enter_rel_tempService enter_rel_tempService;

    @Autowired
    private Item_rel_temService item_rel_temService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private Check_itemService check_itemService;
    @Autowired
    private EnterpriseService enterpriseService;

    private Enter_rel_temp enter_rel_temp;
    private Enterprise enterprise;
    private Item_rel_tem item_rel_tem = new Item_rel_tem();
    private Check_item check_item;
    private Template template = new Template();


    @Before
    public void before(){
        check_item = new Check_item("nn",
                "mm");
        check_itemService.addCheck_item(check_item);
        template.setNametemplate("kl");
        template.setDesctemplate("ml");
        templateService.createTemplate(template);
        item_rel_tem.setIditem(check_item.getId_item());
        item_rel_tem.setIdtemplate(template.getIdtemplate());
        item_rel_temService.addItem_rel_tem(item_rel_tem);
        enterprise = new Enterprise("AAASSSQ","BigBossA", "normal","4673265874387567834","product","food", "mantou", "Jack","123413");
        enterpriseService.addEnterprise(enterprise);

    }

    @Test
    public void enter_rel_temp(){
        //add a enter_rel_temp and find it
        enter_rel_temp = new Enter_rel_temp(item_rel_tem.getIditemreltem(),enterprise.getCodee(),0);
        enter_rel_tempService.addEnter_rel_temp(enter_rel_temp);
        Assert.assertNotEquals(0,enter_rel_tempService.findEnter_rel_temp(item_rel_tem.getIditemreltem(),enterprise.getCodee()).getIdenterreltemp());
        Assert.assertNotEquals(null, enter_rel_tempService.findEnter_rel_temp(enter_rel_temp.getIdenterreltemp()));


    }
    @After
    public void endWork(){
        templateService.deleteTemplate(template);
        check_itemService.deleteCheck_item(check_item.getName_item());
        enterpriseService.deleteEnterprise(enterprise.getNamee());
    }
}

