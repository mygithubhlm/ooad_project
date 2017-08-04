package com.example.demo.service;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.entity.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lemonhuang on 2017/6/19.
 */
@Component
public class EnterpriseService {
    @Autowired
    EnterpriseDao enterpriseDao;


    //to be continued because of the status of enterprise
    public String addEnterprise(Enterprise enterprise){
        Enterprise temp = findEnterprise(enterprise.getNamee());
        if (temp!=null)
            return "fail";
        else{
            enterprise.setStatuse(finishInformation(enterprise)?
                    "finished":"unfinished");
            enterpriseDao.save(enterprise);
            return "successful";
        }
    }

    //find a enterprise
    public Enterprise findEnterprise(String namee){
        return enterpriseDao.findByNamee(namee);
    }

    public Enterprise findEnterpriseByCode(String codee){
        return enterpriseDao.findOne(codee);
    }

    public String deleteEnterprise(String namee){
        Enterprise temp = findEnterprise(namee);
        if (temp==null)
            return "fail";
        enterpriseDao.delete(findEnterprise(namee));
        return "successful";
    }

    public String updateEnterprise(Enterprise enterprise){
        Enterprise enterprise1 = findEnterprise(enterprise.getNamee());
        if (enterprise1==null)
            return "fail";
        if (finishInformation(enterprise))
            enterprise.setStatuse("finished");
        else
            enterprise.setStatuse("unfinished");
        enterpriseDao.save(enterprise);
        return "successful";
    }

    //check whether the enterprise has filled all information
    public boolean finishInformation(Enterprise enterprise){
        if (enterprise.getTrade()==null||enterprise.getTrade()==null||
                enterprise.getCatemanage()==null||enterprise.getLinkman()==null||
                enterprise.getTellinkman()==null){
            return false;
        }
        return true;
    }
}
