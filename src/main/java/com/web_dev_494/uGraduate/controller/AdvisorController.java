package com.web_dev_494.uGraduate.controller;

import com.web_dev_494.uGraduate.entity.*;
import com.web_dev_494.uGraduate.service.MajorService;
import com.web_dev_494.uGraduate.service.ProfessorService;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// All endpoints starting with /advisor/**
// REMEMBER: Don't hardcode context path in the jsp files.. use ${pageContext.request.contextPath}
@Controller
@RequestMapping("/advisor")
public class AdvisorController {

    // Instances of the Service Class to run the HQL queries
    private StudentService studentService;
    private MajorService majorService;
    private ProfessorService professorService;
    private SectionService sectionService;

    // Constructor Injection
    @Autowired
    public AdvisorController(StudentService studentService, MajorService majorService,
                             ProfessorService professorService, SectionService sectionService){
        this.studentService = studentService;
        this.majorService = majorService;
        this.professorService = professorService;
        this.sectionService = sectionService;
    }

    @RequestMapping("/home")
    public String home(){
        return "advisor_mappings/admin-homepage";
    }

    @RequestMapping("/searchStudent")
    public String searchStudent(Model model){
        return "advisor_mappings/search-student-page";
    }
    
    //@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/addStudent")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "advisor_mappings/add-student-page";
    }

    @PostMapping("/addedStudent")
    public String addStudent(@ModelAttribute("student") Student student, Model model){

        student.setId(0);
        // Logic for the student username saving is in studentDAOFunctionality
        System.out.println("Id is: " + student.getId());
        student.setUsername("temp" + student.getFirstName() + student.getLastName());
        studentService.save(student);
        model.addAttribute("student", student);
        return "advisor_mappings/add-results-page";
    }

    //---------------------------------------------------------------
    // @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/students")
    public String findAll(Model model){
        model.addAttribute("students", studentService.findAll());
        return "testAll";
    }
    
    //@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/studentById")
    public String getStudent(@RequestParam("studentId") int studentId, Model model){

        Student student = studentService.findById(studentId);
        if(student == null){
            throw new RuntimeException("Student not found - " + studentId);
        }
        model.addAttribute("student", student);
        return "advisor_mappings/search-results-page-id";
    }

    @RequestMapping("/studentByName")
    public String getStudentName(@RequestParam("studentName") String studentName, Model model){

        List<Student> students = studentService.findByName(studentName);
        model.addAttribute("students", students);

        return "advisor_mappings/search-results-page";
    }

    @RequestMapping("/testQueries")
    public String testQ(Model model){

        // Saves a new Major!
        /*
        Major major = new Major(0, "Underwater Basket Weaving");
        majorService.save(major);
        */

        // Finds a list of Majors
        /*
        List<Major> majors = majorService.findAll();
        model.addAttribute("majors", majors);
        */

        //Major major = majorService.findById(1);
        //model.addAttribute("majors", major);



        // Joining works now!



        //Professor testProf = new Professor(0, "Mike");
        //professorService.save(testProf);

        //Section testSection = new Section("CS101", 0);
        //sectionService.save(testSection);

        //testProf.add(testSection);

        Professor snape = professorService.findByName("snape").get(0);
        System.out.println(snape.getSections());
        return "test/test";
    }


    @RequestMapping("/XSSExample")
    public String xss(){
        return "xss";
    }

    @RequestMapping("/XSSExample-next")
    public String xssNext(@RequestParam("inputValue") String input, Model model){

        model.addAttribute("input", input);
        return "xss-next";
    }

    @RequestMapping("/addSection")
    public String addSection(Model model){

        Section section = new Section();
        model.addAttribute(section);
        model.addAttribute(professorService.findAll());
        return "advisor_mappings/add-section";
    }

    @PostMapping("/addedSection")
    public String addedSection(@ModelAttribute("section") Section section,
                               Model model,
                               @RequestParam("professorName") String professorName){


        sectionService.save(section);
        model.addAttribute(section);

        return "advisor_mappings/add-section-results";
    }

    @RequestMapping("/addProfessor")
    public String addProf(Model model){

        Professor professor = new Professor();
        model.addAttribute("professor",professor);
        return "advisor_mappings/add-professor";

    }

    @PostMapping("/addedProfessor")
    public String addedSection(@ModelAttribute("professor") Professor professor,
                               Model model){

        professor.setProfessorId(0);
        String newUsername = professor.getName() + professor.getProfessorId();

        professorService.save(professor);

        model.addAttribute("professor", professor);
        return "advisor_mappings/add-professor-results-page";
    }

    @RequestMapping("/attachProfessor")
    public String attachProfessor(Model model){
        List<Professor> professors = professorService.findAll();
        List<Section> sections = sectionService.findAll();
        model.addAttribute("professor", professors);
        model.addAttribute("section", sections);
        return "advisor_mappings/attach-professor";
    }

    @RequestMapping("/attachedProfessor")
    public String attachedProfessor(@RequestParam("section") String sectionName,
                                    @RequestParam("prof") String professorName,
                                    Model model){

        Section section = sectionService.findByName(sectionName).get(0);
        Professor professor = professorService.findByName(professorName).get(0);

        professor.add(section);
        section.setProfessor(professor);

        professorService.save(professor);
        sectionService.save(section);

        return "advisor_mappings/attached-professor";
    }

}
