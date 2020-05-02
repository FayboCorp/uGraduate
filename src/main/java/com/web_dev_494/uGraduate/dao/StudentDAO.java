package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> findAll();  
    public void save(Student student);
    public Student findById(int id);
    public void deleteById(int id);
    public List<Student> findByName(String name);
    public Student findByUsername(String username);
    public void deleteByName(String name);


}
