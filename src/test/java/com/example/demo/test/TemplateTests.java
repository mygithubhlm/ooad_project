package com.example.demo.test;

import com.example.demo.entity.Template;
import com.example.demo.service.TemplateService;
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
public class TemplateTests {
    @Autowired
    TemplateService templateService;

    private Template template;

    @Before
    public void Before(){
        template = new Template("newTemplate1","just for test!",0);
    }

    @Test
    public void template(){
        //add a template
        templateService.createTemplate(template);
        template = templateService.findTemplate("newTemplate1");
        Assert.assertEquals("newTemplate1", templateService.findTemplate(template.getIdtemplate()).getNametemplate());

        //try to modify or delete the template, have not published, we can delete or modify it
        template.setDesctemplate("changed description");
        templateService.updateTemplate(template);
        Assert.assertEquals("changed description", templateService.findTemplate("newTemplate1").getDesctemplate());
        templateService.deleteTemplate(template);
        Assert.assertEquals(null, templateService.findTemplate("newTemplate1"));

		/*
		this function need to be changed!
		 */
        templateService.createTemplate(template);
        template = templateService.findTemplate("newTemplate1");

		//publish a template and update it to the database
		template.setStatustemplate(1);
		templateService.updateTemplate(template);
		Assert.assertEquals(1,templateService.findTemplate("newTemplate1").getStatustemplate());

		//try to delete the template, already published, so can't delete
		templateService.deleteTemplate(template);
		Assert.assertEquals("newTemplate1",templateService.findTemplate("newTemplate1").getNametemplate());

        //try to modify the template, already published, so can't modify
		template.setStatustemplate(0);
		templateService.updateTemplate(template);
		Assert.assertEquals(1,templateService.findTemplate("newTemplate1").getStatustemplate());
    }
}
