package com.example.demo.test;

import com.example.demo.entity.Enterprise;
import com.example.demo.service.EnterpriseService;
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
public class EnterpriseTests {
    @Autowired
    private EnterpriseService enterpriseService;

    private Enterprise enterprise;

    @Before
    public void before(){
        enterprise = new Enterprise("AAASSS","BigBoss", "normal","4673265874387567834","product","food", "mantou", "Jack","123413");
    }

    @Test
    public void enterprise(){
        //add a enterprise and find
        enterpriseService.addEnterprise(enterprise);
        Assert.assertEquals("BigBoss",enterpriseService.findEnterpriseByCode(enterprise.getCodee()).getNamee());

        //modify the enterprise
        enterprise.setLinkman("Bob");
        enterpriseService.updateEnterprise(enterprise);
        Assert.assertEquals("Bob", enterpriseService.findEnterpriseByCode(enterprise.getCodee()).getLinkman());

        //delete the enterprise
        enterpriseService.deleteEnterprise(enterprise.getNamee());
        Assert.assertEquals(null, enterpriseService.findEnterprise("BigBoss"));

    }

}
