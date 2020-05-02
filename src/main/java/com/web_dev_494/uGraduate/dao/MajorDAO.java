package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Major;

import java.util.List;

public interface MajorDAO {

    // checked!
    public List<Major> findAll();

    // saved
    public void save(Major major);

    // lookin gooooooood
    public Major findById(int id);

    public void deleteById(int id);

}
