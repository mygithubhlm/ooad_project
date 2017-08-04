package com.example.demo.test;

/**
 * Created by lemonhuang on 2017/6/20.
 */
import com.example.demo.entity.Check_item;
import com.example.demo.service.Check_itemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Check_itemTests {
    @Autowired
    private Check_itemService check_itemService;

    private Check_item check_item;



    @Before
    public void before(){
        check_item = new Check_item("name1","con1");
    }
    @Test
    public void testCheck_item() {
        //add check_item and find it
        check_itemService.addCheck_item(check_item);
        Assert.assertNotEquals(null,check_itemService.findCheck_item(check_item.getName_item()));
        Assert.assertNotEquals(0,check_itemService.findCheck_item("name11").getId_item());

        //update check_item
        check_item = check_itemService.findCheck_item(check_item.getName_item());
        Assert.assertNotEquals(null,check_item);
        check_item.setContent_item("con2");
        Assert.assertEquals("successful",check_itemService.updateCheck_item(check_item));
        Assert.assertEquals("con2", check_itemService.findCheck_item(check_item.getName_item()).getContent_item());

        //delete the check_item
        Assert.assertEquals("successful",check_itemService.deleteCheck_item(check_item.getName_item()));
        Assert.assertEquals(null,check_itemService.findCheck_item(check_item.getName_item()));
    }


//    @Test
//    public void updateCheck_item(){
//
//        check_item = new Check_item("name2","condsaf");
//        check_itemService.addCheck_item(check_item);
//        Check_item upcheck_item = check_itemService.findCheck_item("name2");
//        if(upcheck_item==null)
//            System.out.println("check_item is");
//        else
//            System.out.println("check_item is not");
//
////        check_item.setContent_item("con2");
////        System.out.println("getname: "+upcheck_item.getName_item());
////        check_item = check_itemService.findCheck_item(check_item.getName_item());
//        String re = check_itemService.updateCheck_item(upcheck_item);
//        Assert.assertEquals("successful",re);
//        //Assert.assertEquals("con2", check_itemService.findCheck_item("name11").getContent_item());
//    }

//    @Test
//    public void deleteCheck_item(){
//        Check_item forDelete1 = check_itemService.findCheck_item("name1");
//        Check_item forDelete2 = check_itemService.findCheck_item("name2");
//        check_itemService.deleteCheck_item(forDelete1.getName_item());
//        Assert.assertEquals(null, check_itemService.findCheck_item(forDelete1.getName_item()));
//        check_itemService.deleteCheck_item(forDelete2.getName_item());
//        Assert.assertEquals(null, check_itemService.findCheck_item(forDelete2.getName_item()));
//    }
}
