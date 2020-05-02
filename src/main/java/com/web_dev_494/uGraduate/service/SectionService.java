package com.web_dev_494.uGraduate.service;

	import com.web_dev_494.uGraduate.entity.Section;

	import java.util.List;

public interface SectionService{

		public void save(Section section);
		public Section findByCRN(int CRN);
		public void deleteByCRN(int CRN);
		public List<Section> findByName(String name);
		public void deleteByName(String name);
		public List<Section> findByMajor(int name);
		List<Section> findAll();
	List<Section> findByStudent(String username);

	}
	

