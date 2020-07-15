package com.web_dev_494.uGraduate.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web_dev_494.uGraduate.dao.SectionDAO;
import com.web_dev_494.uGraduate.entity.Section;

import java.util.List;

@Service 
public class SectionServiceFunctionality implements SectionService {
	
	SectionDAO sectionDAO;
	
	@Autowired
	public SectionServiceFunctionality(SectionDAO sectionDAO){
		this.sectionDAO = sectionDAO;
	}

	private int convertMajor(String name){
		switch (name) {
			case "Computer Science":
				return 1;
			case "Psychology":
				return 2;
			case "Industrial Design":
				return 3;
			case "Business":
				return 4;
			case "Economics":
				return 5;
		}
		return -1;
	}

	@Override
	public void save(Section section) {
		sectionDAO.save(section);
	}

	@Override
	public Section findByCRN(int CRN) {
		return sectionDAO.findByCRN(CRN);
	}

	@Override
	public void deleteByCRN(int CRN) {
		sectionDAO.deleteByCRN(CRN);
	}

	@Override
	public List<Section> findByName(String name) {
		return sectionDAO.findByName(name);
	}

	@Override
	public List<Section> findAll() {
		return sectionDAO.findAll();
	}

	@Override
	public void deleteByName(String name) {
		sectionDAO.deleteByName(name);
	}

	@Override
	public List<Section> findByMajor(String name) {
		return sectionDAO.findByMajor(convertMajor(name));
	}

	@Override
	public List<Section> findByStudent(String username) {
		return sectionDAO.findByStudent(username);
	}

	@Override
	public void dropStudent(Section section) {
		sectionDAO.dropStudent(section);
	}
}
