package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.Professor;
import com.web_dev_494.uGraduate.entity.Student;

import java.util.List;

public interface ProfessorDAO {
	
	//update
	public void save(Professor p);
	List<Professor> findAll();
	public Professor findById(int id);
	public void deleteById(int id);
	public List<Professor> findByName(String name);
	public void deleteByName(String name);

}
