package com.example.demo.service;

import com.example.demo.dao.Enter_rel_tempDao;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.ExamineDao;
import com.example.demo.entity.*;
import com.example.demo.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/6/20.
 */
@Service
public class EnterpriseActionService {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private Enter_rel_tempService enter_rel_tempService;
    @Autowired
    private ExamineService examineService;
    @Autowired
    private Item_rel_temService item_rel_temService;
    @Autowired
    private ExamineDao examineDao;
    @Autowired
    private Enter_rel_tempDao enter_rel_tempDao;
    @Autowired
    private EnterpriseDao enterpriseDao;

    //show all templates that the enterprise need to complete or have completed
    public List<Template> showTemplatesAboutEn(Enterprise enterprise){
        List<Examine> examines = examineDao.findByCodee(enterprise.getCodee());
        List<Template> templates = new ArrayList<Template>();
        for (Examine examine:examines) {
            templates.add(
                    templateService.findTemplate(examine.getIdtemplate()));
        }
        return templates;
    }

    //show all the items in a template
    public List<Check_item> showItemsInTem(Template template){
        return templateService.showItemsInTem(template);
    }

    //the enterprise has finished the work of one check item in specific template
    public String finishItemInTem(Enterprise enterprise,Check_item check_item,Template template){
        try {
            Item_rel_tem item_rel_tem = item_rel_temService.findItem_rel_tem(
                    check_item.getId_item(),template.getIdtemplate()
            );
            Enter_rel_temp enter_rel_temp = enter_rel_tempService.
                    findEnter_rel_temp(item_rel_tem.getIditemreltem(),
                            enterprise.getCodee());
            enter_rel_temp.setEndOnot(1);
            enter_rel_tempDao.save(enter_rel_temp);
            //when done one work,check whether all work is done
            tryFinishingAll(enterprise,template);
            return "successful";
        }
        catch (MyException e){
            e.printStackTrace();
            return "fail";
        }
    }

    //show all the enterprises in a park
    public Iterable<Enterprise> showAllEnterprises(){
        return enterpriseDao.findAll();
    }

    /**see whether the enterprise has finished all the check work,
     * if so,change the examine status*/
    public String tryFinishingAll(Enterprise enterprise,Template template){
        try {
            List<Enter_rel_temp> enter_rel_temps = enter_rel_tempService.findTheseByTemAndEnter(template,enterprise);
            for (Enter_rel_temp ent_r_temp:enter_rel_temps) {
                if (ent_r_temp.getEndOnot()==0)
                    return "successful";
            }
            Examine examine = examineService.findExamine(
                    enterprise.getCodee(),template.getIdtemplate()
            );
            examine.setComonot(1);
            examineDao.save(examine);
            return "successful";
        }
        catch (MyException e){
            e.printStackTrace();
            return "fail";
        }
    }
}
