package com.web_dev_494.uGraduate.service;

public interface CompletedService {

    public String getGrade(int crn, int studentId);
    public void completeClass(String grade, int studentId, int crn);

}
