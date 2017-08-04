package com.example.demo.actionTest;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.dao.TemplateDao;
import com.example.demo.entity.Check_item;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Item_rel_tem;
import com.example.demo.entity.Template;
import com.example.demo.exception.CantFindTableException;
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
 * Created by think on 2017/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateActionTests {

    @Autowired
    private TemplateActionService templateActionService;
    @Autowired
    private Check_itemService check_itemService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private Item_rel_temService item_rel_temService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ExamineService examineService;
    @Autowired
    private Enter_rel_tempService enter_rel_tempService;
    /*
     these two Dao just for delete data from database,
     because if the template is published,
     some data can't be deleted with Service in our design.
     */
    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private Check_itemDao check_itemDao;

    private Check_item check_item = new Check_item();
    private Template template = new Template();
    private Enterprise enterprise = new Enterprise();

    //some initial work is done in before part
    @Before
    public void intial(){
        check_item.setName_item("good item");
        check_item.setContent_item("check some necessary items");
        template.setNametemplate("new template");
        template.setDesctemplate("a good template to be executed");
        check_itemService.addCheck_item(check_item);
        templateService.createTemplate(template);
        enterprise.setCodee("2333");
        enterprise.setNamee("new factory");
        enterprise.setCodeorg("2");
        enterpriseService.addEnterprise(enterprise);
    }

    //
    @Test
    public void testAddItemToTem(){
        templateActionService.addItemToTem(template,check_item);
        Assert.assertNotEquals(null,item_rel_temService.
                findItem_rel_tem(check_item.getId_item(),
                        template.getIdtemplate()
        ));
    }

    @Test
    public void testDeleteItemFromTemplate(){
        templateActionService.addItemToTem(template,check_item);
        templateActionService.deleteItemFromTem(template,check_item);
        Assert.assertEquals(null,item_rel_temService.findItem_rel_tem
                (check_item.getId_item(),template.getIdtemplate()));
    }

    @Test
    public void testShowItemsInTem(){
        final int NUMBER = 5;
        for (int i = 0; i < NUMBER; i++) {
            Check_item check_item = new Check_item("item"+i
                    ,"content1");
            check_itemService.addCheck_item(check_item);
            templateActionService.addItemToTem(template,check_item);
            Assert.assertNotEquals(null,check_item.getId_item());
        }
        Assert.assertEquals(NUMBER,
                item_rel_temService.findByTemplate(template).size());
        for (int i = 0; i < NUMBER; i++) {
            check_itemService.deleteCheck_item("item"+i);
        }
    }

    @Test
    public void testSendTemToEnter(){
        templateActionService.addItemToTem(template,check_item);
        templateActionService.sendTemToEnter(template,enterprise
                ,"2017-01-02", "2018-01-01");
        Assert.assertEquals(1,template.getStatustemplate());
        Assert.assertEquals(0,
                examineService.findExamine(enterprise.getCodee()
        ,template.getIdtemplate()).getComonot());
        Assert.assertEquals(1,enter_rel_tempService.
                findTheseByTemAndEnter(template,enterprise).size());
    }

    @Test
    public void testWithdrawItemsFromEnter(){
        templateActionService.addItemToTem(template,check_item);
        templateActionService.sendTemToEnter(template,enterprise
                ,"2017-01-02", "2018-01-01");
        templateActionService.withdrawTemFromEnter(template,enterprise);
        try {
            examineService.findExamine(
                    enterprise.getCodee(),template.getIdtemplate());
        }
        catch (Exception e){
            Assert.assertEquals(CantFindTableException.class.getName(),
                    e.getClass().getName()
                    );
        }

        Assert.assertEquals(0,enter_rel_tempService.
                findTheseByTemAndEnter(template,enterprise).size());
    }

    @Test
    public void testShowAllEnterInTem(){
        templateActionService.addItemToTem(template,check_item);
        templateActionService.sendTemToEnter(template,enterprise
                ,"2017-01-02", "2018-01-01");
        Assert.assertEquals(1,
        templateActionService.showAllEnterInTem(template).size());
    }

    @Test
    public void testShowAllTemplates(){
        Iterable<Template> templates =
                templateActionService.showAllTemplates();
        Assert.assertNotEquals(null,templates);
        Assert.assertEquals(1,(int)templates.spliterator().getExactSizeIfKnown());
    }

    @After
    public void endWork(){
//        System.out.println(check_item.getContent_item());
//        check_itemService.deleteCheck_item(check_item.getName_item());
        check_itemDao.delete(check_item);
        templateDao.delete(template);
        enterpriseService.deleteEnterprise(enterprise.getNamee());
    }

}
