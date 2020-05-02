package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserDAOFunctionality implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOFunctionality(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User getByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User s where s.username =:username" );
        query.setParameter("username", username);

        if (query.getResultList() == null){
            return null;
        }

        return (User) query.getResultList().get(0);
    }
}
