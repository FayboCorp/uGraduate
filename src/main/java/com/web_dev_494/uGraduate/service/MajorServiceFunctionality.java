package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.dao.MajorDAO;
import com.web_dev_494.uGraduate.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorServiceFunctionality implements MajorService {

    MajorDAO majorDAO;

    @Autowired
    public MajorServiceFunctionality(MajorDAO majorDAO){
        this.majorDAO = majorDAO;
    }

    @Override
    public List<Major> findAll() {
        return majorDAO.findAll();
    }

    @Override
    public void save(Major major) {
        majorDAO.save(major);
    }

    @Override
    public Major findById(int id) {
        return majorDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        majorDAO.deleteById(id);
    }
}
