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
    public String getGrade(int crn, int studentId) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from CompletedSections c where c.id=:id");
        query.setParameter("id", studentId);

        List<CompletedSections> sections = query.getResultList();

        for(CompletedSections x : sections){
            if(x.getSection().getCRN() == crn){
                return x.getGrade();
            }
        }

        return "I";
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

