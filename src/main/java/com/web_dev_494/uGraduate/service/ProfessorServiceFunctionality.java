package com.web_dev_494.uGraduate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web_dev_494.uGraduate.dao.ProfessorDAO;
import com.web_dev_494.uGraduate.dao.SectionDAO;
import com.web_dev_494.uGraduate.entity.Professor;

import java.security.PublicKey;
import java.util.List;

@Repository
public class ProfessorServiceFunctionality implements ProfessorService {
	
	ProfessorDAO professorDAO;

	@Autowired
	public ProfessorServiceFunctionality(ProfessorDAO professorDAO){
		this.professorDAO = professorDAO;
	}

	@Override
	public void save(Professor p) {
		professorDAO.save(p);
		
	}

	@Override
	public List<Professor> findAll() {
		return professorDAO.findAll();
	}


	@Override
	public List<Professor> findByName(String name) {
		return professorDAO.findByName(name);
	}

	@Override
	public Professor findById(int id) {
		return professorDAO.findById(id);
	}
}
