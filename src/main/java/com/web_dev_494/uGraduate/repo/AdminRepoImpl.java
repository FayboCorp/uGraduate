package com.web_dev_494.uGraduate.repo;

import com.web_dev_494.uGraduate.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@Service
public class AdminRepoImpl  implements AdminRepo{

    private EntityManager entityManager;

    @Autowired
    AdminRepoImpl(EntityManagerFactory entityManager){
        this.entityManager = entityManager.createEntityManager();
    }

    @Override
    public User findByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User s where  s.username = :username");
        query.setParameter("username", username);

        return (User) query.getResultList().get(0);
    }
}
