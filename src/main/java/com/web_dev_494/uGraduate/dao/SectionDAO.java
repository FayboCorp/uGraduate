package com.web_dev_494.uGraduate.dao;
import com.web_dev_494.uGraduate.entity.Professor;
import com.web_dev_494.uGraduate.entity.Section;

import java.util.List;

public interface SectionDAO {
	
	//read: find by CRN
	 //public Section find(int CRN);
	 
	//create:  adding a section

	public void save(Section section);
	public Section findByCRN(int CRN);
	public void deleteByCRN(int CRN);
	public List<Section> findByName(String name);
	public void deleteByName(String name);
	public List<Section> findByMajor(int name);
	List<Section> findAll();
	List<Section> findByStudent(String username);


}
