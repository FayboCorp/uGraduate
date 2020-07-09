package com.web_dev_494.uGraduate.dao;

public interface CompletedDAO {

    public String getGrade(int crn, int studentId);
    public void completeClass(String grade, int studentId, int crn);

}
