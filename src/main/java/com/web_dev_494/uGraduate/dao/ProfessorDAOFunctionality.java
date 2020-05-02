package com.web_dev_494.uGraduate.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.web_dev_494.uGraduate.entity.Authorities;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.web_dev_494.uGraduate.entity.Professor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProfessorDAOFunctionality implements ProfessorDAO {

	private EntityManager entityManager;

	@Autowired
	public ProfessorDAOFunctionality(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public void save(Professor p) {
		Session currentSession = entityManager.unwrap(Session.class);

		if(p.getProfessorId() == 0){
			currentSession.save(p);

			Query query = currentSession.createQuery("from Professor p where p.name =: username");
			query.setParameter("username", p.getName());

			Professor updatedP = (Professor) query.getResultList().get(0);
			p.setProfessorId(updatedP.getProfessorId());
			p.setName(p.getName() + p.getProfessorId());
		}
		currentSession.saveOrUpdate(p);
	}

	@Override
	public List<Professor> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query theQuery =
				session.createQuery("from Professor", Professor.class);

		return theQuery.getResultList();
	}

	@Override
	public Professor findById(int id) {

		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//get student
		Query query = currentSession.createQuery("from Professor s where" + " s.id =: profId");
		query.setParameter("profId", id);
		List<Professor> professor =  query.getResultList();

		if(professor.size() == 0){
			return null;
		}

		return professor.get(0);

	}



	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Professor where id=:professorId");
		query.setParameter("professorId", id);

		query.executeUpdate();
	}

	@Override
	public List<Professor> findByName(String name) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createQuery("from Professor s where" + " s.name " +
				"LIKE CONCAT('%', :name, '%') ");

		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public void deleteByName(String name) {

	}
}
