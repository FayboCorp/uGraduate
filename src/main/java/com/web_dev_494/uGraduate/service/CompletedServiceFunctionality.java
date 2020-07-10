package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.dao.CompletedDAO;
import com.web_dev_494.uGraduate.entity.CompletedSections;
import com.web_dev_494.uGraduate.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletedServiceFunctionality implements CompletedService {

    CompletedDAO completedDAO;

    @Autowired
    public CompletedServiceFunctionality(CompletedDAO completedDAO) {
        this.completedDAO = completedDAO;
    }

    @Override
    public String getGrade(int crn, int studentId) {
        System.out.println("In service class");
        return completedDAO.getGrade(crn, studentId);
    }

    @Override
    public void completeClass(String grade, int studentId, int crn) {
        completedDAO.completeClass(grade, studentId, crn);
    }

    @Override
    public List<CompletedSections> getClasses(int studentId) {
        return completedDAO.getClasses(studentId);

    }
}
