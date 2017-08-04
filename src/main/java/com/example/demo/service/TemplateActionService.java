package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.exception.TimeException;
import com.example.demo.helper.TimeRelated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
@Service
public class TemplateActionService {
    @Autowired
    private Item_rel_temService item_rel_temService;
    @Autowired
    private ExamineService examineService;
    @Autowired
    private Enter_rel_tempService enter_rel_tempService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private ExamineDao examineDao;
    @Autowired
    private Item_rel_temDao item_rel_temDao;
    @Autowired
    private EnterpriseDao enterpriseDao;
    @Autowired
    private Enter_rel_tempDao enter_rel_tempDao;
    @Autowired
    private TemplateDao templateDao;



    //tested
    public String addItemToTem(Template template, Check_item check_item){
        if (hasException(template,check_item)){
            return "fail";
        }

        int idtemplate = template.getIdtemplate();
        int iditem = check_item.getId_item();

        Item_rel_tem item_rel_tem = new Item_rel_tem();
        item_rel_tem.setIdtemplate(idtemplate);
        item_rel_tem.setIditem(iditem);
        return item_rel_temService.addItem_rel_tem(item_rel_tem);
    }

    //Tested
    public String deleteItemFromTem(Template template,Check_item check_item){
        if (hasException(template,check_item)){
            return "fail";
        }
        Item_rel_tem item_rel_tem = item_rel_temDao.findByIditemAndIdtemplate
                (check_item.getId_item(),template.getIdtemplate());
        if (item_rel_tem==null){
            return "fail";
        }
        //delete according to object
        item_rel_temDao.delete(item_rel_tem);
        return "successful";
    }

    //Tested
    //show all the items in this template
    public List<Check_item> showItemsInTem(Template template){
        return templateService.showItemsInTem(template);
    }

    public List<Examine> showProgressOfTem(Template template){
        List<Examine> examines =
                examineDao.findByIdtemplate(template.getIdtemplate());
        return examines;
    }

    //Tested
    //deliver a template to an enterprise
    public String sendTemToEnter(Template template, Enterprise enterprise,
                                 String begin,String ddl){
        int idtemplate = template.getIdtemplate();
        String codee = enterprise.getCodee();
        if (idtemplate==0)
            throw new NoSuchEntityException(this.getClass().getName()
            ,Template.class);
        if (!enterpriseDao.exists(codee))
            throw new NoSuchEntityException(this.getClass().getName()
            ,Enterprise.class);
        try {
            TimeRelated.convertToDate(begin);
            if (TimeRelated.outOfTime(ddl))
                throw new TimeException(this.getClass().getName());
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        //change the status of template
        template.setStatustemplate(1);
        templateService.updateTemplate(template);
        Examine examine = new Examine();
        examine.setCodee(codee);
        examine.setIdtemplate(idtemplate);
        examine.setTimebegin(begin);
        examine.setTimeddl(ddl);
        examineService.addExamine(examine);
        List<Item_rel_tem> item_rel_tems =
                item_rel_temDao.findByIdtemplate(idtemplate);
        for (Item_rel_tem item_rel_tem:item_rel_tems
             ) {
            Enter_rel_temp enter_rel_temp = new Enter_rel_temp();
            enter_rel_temp.setCodee(codee);
            enter_rel_temp.setIditemreltem(item_rel_tem.getIditemreltem());
            enter_rel_tempService.addEnter_rel_temp(enter_rel_temp);
//            System.out.println("I have added");
        }
        return "successful";
    }

    //Tested
    //call back a template from enterprise
    public String withdrawTemFromEnter(Template template,Enterprise enterprise){
        //there is no need to check whether it has done check work
        Examine examine = examineService.findExamine
                (enterprise.getCodee(),template.getIdtemplate());
        examineService.deleteExamine(examine);
        List<Item_rel_tem> item_rel_tems =
                item_rel_temDao.findByIdtemplate(template.getIdtemplate());
        for (Item_rel_tem item_rel_tem:item_rel_tems
             ) {
            Enter_rel_temp enter_rel_temp = enter_rel_tempService.
                    findEnter_rel_temp(item_rel_tem.getIditemreltem()
                            ,enterprise.getCodee());
            enter_rel_tempDao.delete(enter_rel_temp);
        }
        return "successful";
    }

    //tested
    //show all the enterprises that are related with the template
    public List<Enterprise> showAllEnterInTem(Template template){
        List<Examine> examines =
                examineDao.findByIdtemplate(template.getIdtemplate());
        List<Enterprise> result = new ArrayList<Enterprise>();
        for (Examine examine:examines
             ) {
            result.add(enterpriseDao.findByCodee(examine.getCodee()));
        }
        return result;
    }

    //tested
    //show all the templates in a park
    public Iterable<Template> showAllTemplates(){
        Iterable<Template> templates = templateDao.findAll();
        return templates;
    }

    //it is a help function,it has no business with service logic
    // check whether the process has exception
    public boolean hasException(Template template,Check_item check_item){
        if (template.getStatustemplate()==1){
            return true;
        }
        int idtemplate = template.getIdtemplate();
        int iditem = check_item.getId_item();
        if (idtemplate==0||iditem==0){
            return true;
        }
        return false;
    }

}
