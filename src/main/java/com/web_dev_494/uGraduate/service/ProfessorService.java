package com.web_dev_494.uGraduate.service;

import com.web_dev_494.uGraduate.entity.Major;
import com.web_dev_494.uGraduate.entity.Professor;
import com.web_dev_494.uGraduate.entity.Student;

import java.util.List;

public interface ProfessorService {
	//update
	public void save(Professor p);
	public Professor findById(int id);
	public List<Professor> findByName(String name);
	List<Professor> findAll();

/*

	public List<Student> findAll();
	public void deleteById(int id);
	public List<Student> findByName(String name);
	public void deleteByName(String name);
*/
}
