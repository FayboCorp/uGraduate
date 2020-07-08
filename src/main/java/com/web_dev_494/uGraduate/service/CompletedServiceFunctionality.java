package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.dao.CompletedDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
