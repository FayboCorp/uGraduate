package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.CompletedSections;
import com.web_dev_494.uGraduate.entity.Section;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CompletedDAOFunctionality implements CompletedDAO {

    private EntityManager entityManager;
    private StudentService studentService;
    private SectionService sectionService;

    @Autowired
    public CompletedDAOFunctionality(EntityManager entityManager, StudentService studentService,
                                     SectionService sectionService) {
        this.entityManager = entityManager;
        this.studentService = studentService;
        this.sectionService = sectionService;
    }

    @Override
    public List<CompletedSections> getClasses(int studentId) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("SELECT s from CompletedSections s " +
                "JOIN s.student stu " +
                "WHERE stu.id=:id");
        query.setParameter("id", studentId);
        List<CompletedSections> sections = query.getResultList();
        return sections;
    }

    @Override
    public String getGrade(int crn, int studentId) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("SELECT comSec from CompletedSections comSec " +
                "JOIN comSec.student s " +
                "JOIN comSec.section sec" +
                " where s.id=:id AND sec.CRN =:crn");
        query.setParameter("id", studentId);
        query.setParameter("crn", crn);

        List<CompletedSections> sections = query.getResultList();

        if(sections.size() == 0){
            return "I";
        }
        else if(sections.size() > 1){
            return "More Than 1 Grade... See advisor";
        }
        else{
            return sections.get(0).getGrade();
        }
    }

    // TODO: Test this
    @Override
    public void completeClass(String grade, int studentId, int crn) {
        Session currentSession = entityManager.unwrap(Session.class);
        Section section = sectionService.findByCRN(crn);
        Student student = studentService.findById(studentId);

        CompletedSections completeSection = new CompletedSections(grade, student, section);
        completeSection.setId(0);
        currentSession.save(completeSection);
    }
}

