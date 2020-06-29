package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Authorities;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentDAOFunctionality implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAOFunctionality(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        System.out.println("************FIND ALL");
        Query theQuery =
                currentSession.createQuery("from Student", Student.class);

        return theQuery.getResultList();
    }


    public void grantAuth(Student s) {
        Session currentSession = entityManager.unwrap(Session.class);
        Student student =  currentSession.get(Student.class, s.getId());
        /*
        Query query = currentSession.createQuery("delete from Student where id=:studentId");
        query.setParameter("studentId", id);
         */

    }

    @Override
    @Transactional
    public void save(Student student){
        Session currentSession = entityManager.unwrap(Session.class);
        if(student.getId() == 0){
            currentSession.saveOrUpdate(new User(0,
                    student.getUsername(), "password"));

            Query query = currentSession.createQuery("from User s where s.username =:username" );
            query.setParameter("username", student.getUsername());

            User user = (User) query.getResultList().get(0);

            Authorities authorities = new Authorities(0, "ROLE_STUDENT");
            user.addAuthorities(authorities);
            currentSession.saveOrUpdate(authorities);
            student.setId(user.getId());

            String newUsername = student.getFirstName().charAt(0) + student.getLastName() + student.getId();
            student.setUsername(newUsername);
            System.out.println("New Student: " + student.toString());
            user.setUsername(newUsername);

            currentSession.save(student);

        }
        else {
            currentSession.saveOrUpdate(student);
        }
    }

    @Override
    public Student findById(int id){

        //get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //get student
        Query query = currentSession.createQuery("from Student s where" + " s.id =: stuId");
        query.setParameter("stuId", id);
        List<Student> student =  query.getResultList();

        if(student.size() == 0){
            return null;
        }

        return student.get(0);

    }

    @Override
    public List<Student> findByName(String name) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Student s where" + " s.firstName " +
                "LIKE CONCAT('%', :studentName, '%') OR s.lastName LIKE CONCAT('%', :studentName, '%')");
        query.setParameter("studentName", name);

        return query.getResultList();
    }

    @Override
    public Student findByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Student s where s.username =:username" );
        query.setParameter("username", username);

        return (Student) query.getResultList().get(0);
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("delete from Student where id=:studentId");
        query.setParameter("studentId", id);

        query.executeUpdate();
    }
}
