package com.web_dev_494.uGraduate.controller;

import com.web_dev_494.uGraduate.entity.Section;
import com.web_dev_494.uGraduate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.service.MajorService;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private StudentService studentService;
	private MajorService majorService;
	private SectionService sectionService;
	private Student student;

	@Autowired
	public StudentController(StudentService studentService, MajorService m, SectionService s){
		this.studentService = studentService;
		this.majorService = m;
		this.sectionService = s;
	}

	//student should be able to.. login
	//register
	//see what major they are in
	//see what classes they are enrolled in
	//see what classes they finished
	//see what classes they have left
	//register 
	  
	@RequestMapping("/home")
	public String home(){

		return "student_mappings/studentHome";
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
	    
	@RequestMapping("/courselist")
	public String showCourses(Model model, @AuthenticationPrincipal User user){

		student = studentService.findByUsername(user.getUsername());
		model.addAttribute("student", student);

		List<Section> sections = sectionService.findByMajor(convertMajor(student.getMajor()));
		model.addAttribute("sections", sections);
		return "student_mappings/course-list";
	}

	@RequestMapping("/register")
	public String register (Model model, @AuthenticationPrincipal User user){

		student = studentService.findByUsername(user.getUsername());
		model.addAttribute(student);

		List<Section> sections = sectionService.findByMajor(convertMajor(student.getMajor()));
		model.addAttribute("sections", sections);

		return "student_mappings/register";
	}

	@RequestMapping("/registered")
	public String registered(@RequestParam("section") Section section, @AuthenticationPrincipal User user,
							 Model model) {
		student = studentService.findByUsername(user.getUsername());
		section = sectionService.findByName(section.getClassName()).get(0);

		section.addStudent(student);

		studentService.save(student);

		model.addAttribute("student", student);
		model.addAttribute("section", section);

		return "student_mappings/registered";
	}

	@RequestMapping("/viewRegistered")
	public String viewRegistered(Model model, @AuthenticationPrincipal User user){
		student = studentService.findByUsername(user.getUsername());
		List<Section> sections = sectionService.findByStudent(student.getUsername());
		model.addAttribute("sections", sections);

		/*
		 TODO: This is a temporary hack. This makes extra unnecessary queries. Figure out many to many query
		 !!! Time complexity is unnecessarily O(n^2)
		*/



		return "student_mappings/view-registered";
	}

	@RequestMapping("/changePassword")
	public String changePassword(){

		return "student_mappings/change-password";
	}

}
