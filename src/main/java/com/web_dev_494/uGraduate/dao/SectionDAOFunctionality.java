package com.web_dev_494.uGraduate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.web_dev_494.uGraduate.entity.Professor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web_dev_494.uGraduate.entity.Section;

@Repository
public class SectionDAOFunctionality implements SectionDAO {
	
		private EntityManager entityManager;

	    @Autowired
	    public SectionDAOFunctionality(EntityManager entityManager){
	        this.entityManager = entityManager;
	    }

	    @Override
		 public void save(Section section) {

	    	 Session currentSession = entityManager.unwrap(Session.class);

	         currentSession.saveOrUpdate(section);
		 }

	@Override
	public Section findByCRN(int CRN) {

	    	Session session = entityManager.unwrap(Session.class);
	    	return session.get(Section.class, CRN);
	}

	@Override
	public List<Section> findByName(String name) {

	    	Session session = entityManager.unwrap(Session.class);

	    	Query query = session.createQuery("from Section s where" + " s.className " +
					"LIKE CONCAT('%', :name, '%') ");
			query.setParameter("name", name);

			return query.getResultList();
	}

	@Override
	public List<Section> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query theQuery = session.createQuery("from Section", Section.class);

		return theQuery.getResultList();
	}

	@Override
	public List<Section> findByMajor(int name) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createQuery("from Section s where s.major =:name" );
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	public List<Section> findByStudent(String username) {
	    Session session = entityManager.unwrap(Session.class);

		System.out.println("looking for " + username);

		// TODO: Figure out this many to many query
	    Query query = session.createQuery("SELECT s FROM Section s JOIN s.students a WHERE " +
				"a.username=:susername");
	    query.setParameter("susername", username);

	    return query.getResultList();
	}

	@Override
	public void deleteByName(String name) {

	}

	@Override
	public void deleteByCRN(int id) {

	}
}
