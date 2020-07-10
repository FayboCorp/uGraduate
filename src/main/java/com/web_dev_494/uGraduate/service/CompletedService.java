package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.entity.CompletedSections;
import com.web_dev_494.uGraduate.entity.Section;

import java.util.List;

public interface CompletedService {

    public String getGrade(int crn, int studentId);
    public void completeClass(String grade, int studentId, int crn);
    public List<CompletedSections> getClasses(int studentId);

}
