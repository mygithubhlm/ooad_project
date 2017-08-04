package com.example.demo.actionTest;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.dao.TemplateDao;
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

import java.util.List;

/**
 * Created by lemonhuang on 2017/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnterpriseActionServiceTests {
    @Autowired
    Check_itemService check_itemService;

    @Autowired
    EnterpriseActionService enterpriseActionService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    TemplateService templateService;

    @Autowired
    ExamineService examineService;

    @Autowired
    Item_rel_temService item_rel_temService;

    @Autowired
    Enter_rel_tempService enter_rel_tempService;

    @Autowired
    TemplateActionService templateActionService;

    /*
     these two Dao just for delete data from database,
     because if the template is published,
     some data can't be deleted with Service in our design
     */
    @Autowired
    TemplateDao templateDao;
    @Autowired
    Check_itemDao check_itemDao;

    private Check_item check_item1;
    private Check_item check_item2;

    private Enterprise enterprise1;

    private Template template1;
    private Template template2;

    @Before
    public void before(){
        check_item1 = new Check_item("check_item1","con1");
        check_item2 = new Check_item("check_item2","con2");

        enterprise1 = new Enterprise("AAASSSZ","BigBossZ", "normal","4673265874387567834","product","food", "mantou", "Jack","123413");

        template1 = new Template("tempTemplate","just for test!",0);
        template2 = new Template("tempTemplate2","just for test!",0);

        check_itemService.addCheck_item(check_item1);
        check_itemService.addCheck_item(check_item2);

        enterpriseService.addEnterprise(enterprise1);

        templateService.createTemplate(template1);
        templateService.createTemplate(template2);

    }

    /*
      display all the templates of a enterprises,
      and before the @Before is executed, the database is empty
     */
    @Test
    public void showAllTempOfEnter(){
        //publish the template1 to enterprise1
        templateActionService.sendTemToEnter(template1,enterprise1,"2017-06-12","2017-10-12");
        //publish the template2 to enterprise2
        templateActionService.sendTemToEnter(template2,enterprise1,"2017-06-12","2017-10-12");
        // test the function getting all the templates of the enterprise1
        List<Template> templates = enterpriseActionService.showTemplatesAboutEn(enterprise1);
        Assert.assertEquals(2,templates.size());
    }

    /*
      display all the check_items of a template
      and before the @Before is executed, the database is empty
     */
    @Test
    public void showAllItemOfTemp(){
        // insert check_item1 into template1
        templateActionService.addItemToTem(template1,check_item1);
        // insert check_item2 into template1
        templateActionService.addItemToTem(template1,check_item2);
        // test the function getting all the Check_items of the template1
        List<Check_item> items = enterpriseActionService.showItemsInTem(template1);
        Assert.assertEquals(2,items.size());
    }

    /*
     show all the enterprises stored in the database
     and before the @Before is executed, the database is empty
     */
    @Test
    public void showAllEnter(){
        // test the function getting all the enterprises from database
        Iterable<Enterprise> enterprises = enterpriseActionService.showAllEnterprises();
        Assert.assertNotEquals(null,enterprises);
        // we assert one enterprise into database, so the num of 'enterprises' should be 1
        Assert.assertEquals(1,(int)enterprises.spliterator().getExactSizeIfKnown());
    }

    /*
     a enterprise finished a check_item
     and before the @Before is executed, the database is empty
     */
    @Test
    public void enterpriseFinishItem(){
        // add 'check_item1' to 'template1'
        templateActionService.addItemToTem(template1,check_item1);
        // add 'check_item2' to 'template1'
        templateActionService.addItemToTem(template1,check_item2);
        // publish the 'template1' to 'enterprise1'
        templateActionService.sendTemToEnter(template1,enterprise1,"2017-07-01","2017-10-01");
        //enterprise1 finished the check_item1 in template1
        enterpriseActionService.finishItemInTem(enterprise1,check_item1,template1);

        Item_rel_tem item_rel_tem = item_rel_temService.findItem_rel_tem(check_item1.getId_item(),template1.getIdtemplate());
        Assert.assertNotEquals(null,item_rel_tem);

        //test if the status have changed to 1 (0 is not completed, 1 is completed)
        Assert.assertEquals(1,enter_rel_tempService.findEnter_rel_temp(item_rel_tem.getIditemreltem(),enterprise1.getCodee()).getEndOnot());
    }

    /*
        We should not change anything after test the functions
        so we delete the data that have created in the @Before,
        and also, relation tables has foreign key constraints,
        we just need to delete the data in the entity set, and the relation data in the relation tables will be deleted too.
        e.g. if we delete 'check_item1', the data in  table 'item_rel_tem' will be deleted too.
     */
    @After
    public void after(){
        enterpriseService.deleteEnterprise("BigBossZ");
        templateDao.delete(template1);
        templateDao.delete(template2);
        check_itemDao.delete(check_item1);
        check_itemDao.delete(check_item2);
    }
}
