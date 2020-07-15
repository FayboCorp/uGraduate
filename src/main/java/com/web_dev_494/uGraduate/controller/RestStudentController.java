package com.web_dev_494.uGraduate.controller;

import com.web_dev_494.uGraduate.entity.CompletedSections;
import com.web_dev_494.uGraduate.entity.Section;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.service.CompletedService;
import com.web_dev_494.uGraduate.service.MajorService;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
TODO:
 - Finish making this completed courses.
 - maybe populate the database a bit more?

 */

@RestController
@RequestMapping("/student/api")
public class RestStudentController {

    private StudentService studentService;
    private MajorService majorService;
    private SectionService sectionService;
    private Student student;
    private CompletedService completedService;

    @Autowired
    public RestStudentController(StudentService studentService,
                                 MajorService majorService, SectionService sectionService, CompletedService completedService){
        this.studentService = studentService;
        this.majorService = majorService;
        this.sectionService = sectionService;
        this.completedService = completedService;
    }

    // Singleton Pattern(ish). Makes sure there is only 1 instance of student for tracking purposes
    private void instantiateStudent(String username){
        if(this.student == null){
            this.student = studentService.findByUsername(username);
        }
    }

    @GetMapping("/home")
    public List<Section> home(){

        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Section> sections = sectionService.findByStudent(this.student.getUsername());

        return sections;
    }

    @GetMapping("/available-sections")
    public List<Section> availableSections(){
        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Section> sections = sectionService.findByMajor(this.student.getMajor());
        return sections;
    }

    @PostMapping("/register/{CRN}")
    public Section register(@PathVariable int CRN){
        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Section section = sectionService.findByCRN(CRN);

        section.addStudent(this.student);
        studentService.save(this.student);

        return section;
    }

    @DeleteMapping("/register/{CRN}")
    public Section dropClass(@PathVariable int CRN){
        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Student studentS = studentService.findById(this.student.getId());
        Section section = sectionService.findByCRN(CRN);

        section.removeStudent(studentS);

        sectionService.dropStudent(section);
        studentService.dropClass(studentS);

        return section;
    }

    @GetMapping("/register")
    public List<Section> registeredList() {

        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Section> sections = sectionService.findByStudent(this.student.getUsername());

        return sections;
    }

    @GetMapping("/passed")
    public List<CompletedSections> passedCourses() {

        instantiateStudent((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<CompletedSections> sections = completedService.getClasses(this.student.getId());
        System.out.println(sections);
        return sections;
    }

}
