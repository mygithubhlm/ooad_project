package com.example.demo.test;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Examine;
import com.example.demo.entity.Template;
import com.example.demo.service.EnterpriseService;
import com.example.demo.service.ExamineService;
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
public class ExamineTests {
    @Autowired
    ExamineService examineService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    TemplateService templateService;


    private Examine examine;
    private Enterprise enterprise;
    private Template template;

    @Before
    public void before(){
        enterprise = new Enterprise("AAASSS","BigBoss", "normal","4673265874387567834","product","food", "mantou", "Jack","123413");
        enterpriseService.addEnterprise(enterprise);

        template = new Template("forTestTemplate1","just for test!",0);

        templateService.createTemplate(template);
        System.out.println("template_id: "+template.getIdtemplate());
        examine = new Examine(enterprise.getCodee(),template.getIdtemplate(),"2017-06-12","2017-10-12");
    }
    @Test
    public void examine(){
        //add an examine and find
        examineService.addExamine(examine);
        Assert.assertNotEquals(0,examineService.findExamine(examine.getCodee(),examine.getIdtemplate()));
        Assert.assertNotEquals(null, examineService.findExamine(examine.getIdexamine()));

        //update the examine
        examine.setTimecomp("2017-08-08");
        examineService.updateExamine(examine);
        Assert.assertEquals("2017-08-08",examineService.findExamine(examine.getIdexamine()).getTimecomp());

        //delete the examine
        examineService.deleteExamine(examine);
        Assert.assertEquals(null,examineService.findExamine(examine.getIdexamine()));
    }

    @After
    public void after(){
        enterpriseService.deleteEnterprise(enterprise.getNamee());
        templateService.deleteTemplate(template);
    }

}
