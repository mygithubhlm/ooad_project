package com.example.demo.service;

import com.example.demo.dao.ExamineDao;
import com.example.demo.entity.Examine;
import com.example.demo.entity.Template;
import com.example.demo.exception.CantFindTableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by think on 2017/6/19.
 */
@Service
public class ExamineService {
    @Autowired
    private ExamineDao examineDao;

    public Examine findExamine(int idexamine){
        return examineDao.findOne(idexamine);
    }

    public Examine findExamine(String codee, int idtemplate){
        Examine examine =
                examineDao.findByCodeeAndIdtemplate(codee, idtemplate);
        if (examine==null)
            throw new CantFindTableException(
                    this.getClass().getName(),Examine.class);
        return examine;
    }

    public String addExamine(Examine examine){
        Examine examine1 = findExamine(examine.getIdexamine());
        if (examine1!=null)
            return "fail";
        examineDao.save(examine);
        return "successful";
    }

    public String updateExamine(Examine examine){
        Examine examine1 = findExamine(examine.getIdexamine());
        if (examine1==null)
            return "fail";
        examineDao.save(examine);
        return "successful";
    }

    public String deleteExamine(Examine examine){
        if (examine==null)
            return "fail";
        examineDao.delete(examine);
        return "successful";
    }

    //check whether the template is used
    public boolean temIsUsed(Template template){
        List<Examine> examines =
                examineDao.findByIdtemplate(template.getIdtemplate());
        return examines.size()==0?false:true;
    }
}
