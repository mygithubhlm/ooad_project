package com.example.demo;

import com.example.demo.dao.Check_itemDao;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.sun.tools.javac.comp.Enter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OoadProjectApplicationTests {
	@Autowired
	private Check_itemService check_itemService;

	@Autowired
	private Enter_rel_tempService enter_rel_tempService;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private ExamineService examineService;

	@Autowired
	private Item_rel_temService item_rel_temService;

	@Autowired
	private TemplateService templateService;

	private Check_item check_item;
	private Check_item check_item1;
	private Template template;
	private Template template1;
	private Enterprise enterprise;
	private Item_rel_tem item_rel_tem;
	private Item_rel_tem item_rel_tem1;
	private Item_rel_tem item_rel_tem2;

	@Before
	public void beforeCheck_item(){
		check_item = new Check_item("name1","con1");
		//check_item1 = new Check_item("name2","con2");
	}

	@Test
	public void check_itemLoads() {

		check_itemService.addCheck_item(check_item);
//		Assert.assertEquals(4, check_itemDao.findAll().size());
		Assert.assertNotEquals(0,check_item.getId_item());

		System.out.println("保存之后：");
	}

//	@Before
//	public void beforeTemplate(){
//		template = new Template("newTemplate1","just for test!",0);
//		template1 = new Template("newTemplate2","just for test2",0);
//	}
//	@Test
//	public void template(){
//		//add a template
//		templateService.createTemplate(template);
//		template = templateService.findTemplate("newTemplate1");
//		Assert.assertEquals("newTemplate1", templateService.findTemplate(template.getIdtemplate()).getNametemplate());
//
//		//try to modify or delete the template, have not published, we can delete or modify it
//		template.setDesctemplate("changed description");
//		templateService.updateTemplate(template);
//		Assert.assertEquals("changed description", templateService.findTemplate("newTemplate1").getDesctemplate());
//		templateService.deleteTemplate(template);
//		Assert.assertEquals(null, templateService.findTemplate("newTemplate1"));
//
//		/*
//		this function need to be changed!
//		 */
//		templateService.createTemplate(template);
//		template = templateService.findTemplate("newTemplate1");
//
////		//publish a template and update it to the database
////		template.setStatustemplate(1);
////		templateService.updateTemplate(template);
////		Assert.assertEquals(1,templateService.findTemplate("newTemplate1").getStatustemplate());
////
////		//try to delete the template, already published, so can't delete
////		templateService.deleteTemplate(template);
////		Assert.assertEquals("newTemplate1",templateService.findTemplate("newTemplate1").getNametemplate());
//
//		//try to modify the template, already published, so can't modify
////		template.setStatustemplate(0);
////		templateService.updateTemplate(template);
////		Assert.assertEquals(1,templateService.findTemplate("newTemplate1").getStatustemplate());
//	}

//	@Before
//	public void beforeEnterprise(){
//		enterprise = new Enterprise("AAASSS","BigBoss", "normal","4673265874387567834","product","food", "mantou", "Jack","123413");
//	}

//	@Test
//	public void enterprise(){
//		//add a enterprise
//		enterpriseService.addEnterprise(enterprise);
//		System.out.println(enterprise.getCodee());
//
//		Assert.assertEquals("BigBoss",enterpriseService.findEnterpriseByCode(enterprise.getCodee()).getNamee());
//
//		//modify the enterprise
//		enterprise.setLinkman("Bob");
//		enterpriseService.updateEnterprise(enterprise);
//		Assert.assertEquals("Bob", enterpriseService.findEnterpriseByCode(enterprise.getCodee()).getLinkman());
//
//		//delete the enterprise
//		enterpriseService.deleteEnterprise(enterprise.getNamee());
//		Assert.assertEquals(null, enterpriseService.findEnterprise("BigBoss"));
//
//	}

//	@Before
//	public void beforeItem_rel_tem(){
//		check_itemService.addCheck_item(check_item);
//		check_item = check_itemService.findCheck_item("name1");
//
//
//		check_itemService.addCheck_item(check_item1);
//		check_item1 = check_itemService.findCheck_item("name2");
//
//		templateService.createTemplate(template);
//		template = templateService.findTemplate("newTemplate1");
//
//		templateService.createTemplate(template1);
//		template1 = templateService.findTemplate("newTemplate2");
//
//
//		item_rel_tem = new Item_rel_tem(check_item.getId_item(),template.getIdtemplate());
//		item_rel_tem1 = new Item_rel_tem(check_item1.getId_item(), template.getIdtemplate());
//		item_rel_tem2 = new Item_rel_tem(check_item.getId_item(), template1.getIdtemplate());
//
//	}

//	@Test
//	public void item_rel_tem(){
//
//		//add new item_rel_tems to database
//		item_rel_temService.addItem_rel_tem(item_rel_tem);
//		Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(item_rel_tem.getIditem(),item_rel_tem.getIdtemplate()));
//
//		item_rel_temService.addItem_rel_tem(item_rel_tem1);
//		Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(check_item1.getId_item(),template.getIdtemplate()));
//
//		item_rel_temService.addItem_rel_tem(item_rel_tem2);
//		Assert.assertNotEquals(0,item_rel_temService.findItem_rel_tem(check_item1.getId_item(),template.getIdtemplate()));
//
//		//delete a template, and the relative item_rel_tem will be all deleted
//		templateService.deleteTemplate(template1);
//		Assert.assertEquals(null, item_rel_temService.findItem_rel_tem(item_rel_tem2.getIditemreltem()));
//
//		//delete a check_item, and the relative item_rel_tem will be all deleted
//		check_itemService.deleteCheck_item(check_item.getName_item());
//		Assert.assertEquals(null, item_rel_temService.findItem_rel_tem(item_rel_tem.getIditemreltem()));
//
//	}

//	@Before
//	public void beforeEnter_rel_tem(){
//		enterpriseService.addEnterprise(enterprise);
//		templateService.createTemplate(template);
//
//		//Enter_rel_temp enter_rel_temp = new Enter_rel_temp(enterprise.getCodee(),template.getIdtemplate(),);
//	}
//	@Test
//	public void enter_rel_temp(){
//
//	}

}
