package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Section;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CompletedDAOFunctionality implements CompletedDAO {


    private EntityManager entityManager;

    @Autowired
    public CompletedDAOFunctionality(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String getGrade(int crn, int studentId) {
        Session session = entityManager.unwrap(Session.class);


        Query query = session.createQuery("from CompletedSections c where c.id=:id");
        query.setParameter("id", studentId);

        System.out.println("The List: " + query.getResultList());

        return null;
    }
}
