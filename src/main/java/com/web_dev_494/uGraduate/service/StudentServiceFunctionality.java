package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.dao.StudentDAO;
import com.web_dev_494.uGraduate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceFunctionality implements StudentService {

    StudentDAO studentDAO;

    @Autowired
    public StudentServiceFunctionality(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    @Transactional
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public Student findByUsername(String username) {
        return studentDAO.findByUsername(username);
    }

    @Override
    public void deleteByName(String name) {
        studentDAO.deleteByName(name);
    }
}
