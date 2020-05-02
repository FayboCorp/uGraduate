package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Major;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MajorDAOFunctionality implements MajorDAO {

    private EntityManager entityManager;

    @Autowired
    public MajorDAOFunctionality(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Major> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Major ", Major.class);
        return query.getResultList();

    }

    @Override
    public void save(Major major) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(major);
    }

    @Override
    public Major findById(int id) {

        Session session = entityManager.unwrap(Session.class);
        return session.get(Major.class, id);

    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Major where id=: majorId");
        query.setParameter("majorId", id);

        query.executeUpdate();
    }
}
