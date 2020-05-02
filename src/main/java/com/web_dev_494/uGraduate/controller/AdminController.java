package com.web_dev_494.uGraduate.controller;


import com.web_dev_494.uGraduate.entity.Professor;
import com.web_dev_494.uGraduate.entity.Section;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.exceptions.ErrorResponse;
import com.web_dev_494.uGraduate.exceptions.ProfessorNotFound;
import com.web_dev_494.uGraduate.exceptions.SectionNotFound;
import com.web_dev_494.uGraduate.exceptions.StudentNotFound;
import com.web_dev_494.uGraduate.service.ProfessorService;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private StudentService studentService;
    private ProfessorService professorService;
    private SectionService sectionService;

    @Autowired
    public AdminController(StudentService studentService, SectionService sectionService,
                           ProfessorService professorService){
        this.professorService = professorService;
        this.studentService = studentService;
        this.sectionService = sectionService;
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

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/professors")
    public List<Professor> getAllProfessors(){
        return professorService.findAll();
    }

    @GetMapping("/sections")
    public List<Section> getAllSections(){
        return sectionService.findAll();
    }

    // Find student by ID
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

       Student student = studentService.findById(studentId);
       if(student == null){
           throw new StudentNotFound("Student Id not found: " + studentId);
       }

        return student;
    }

    @GetMapping("/professors/{professorId}")
    public Professor getProfessor(@PathVariable int professorId){

        Professor professor = professorService.findById(professorId);
        if(professor == null){
            throw new ProfessorNotFound("Professor id not found: " + professorId);
        }
        return professor;
    }

    @GetMapping("/sections/{sectionId}")
    public Section getSection(@PathVariable int sectionId){
        Section section = sectionService.findByCRN(sectionId);
        if(section == null){
            throw new SectionNotFound("Section CRN not found: " + sectionId);
        }
        return section;
    }

    @PostMapping("/students")
    public Student newStudent(@RequestBody Student student){

        System.out.println(student.getFirstName() + " was received");
        student.setId(0);
        student.setUsername("pending");
        studentService.save(student);

        return student;
    }

    @PostMapping("/professors")
    public Professor newProfessor(@RequestBody Professor professor){
        professor.setProfessorId(0);
        professorService.save(professor);
        return professor;
    }

    @PostMapping("/sections")
    public Section newSection(@RequestBody Section section){
        section.setCRN(0);
        sectionService.save(section);
        return section;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        studentService.save(student);
        return student;
    }
    @PutMapping("/sections")
    public Section updateSection(@RequestBody Section section){
        sectionService.save(section);
        return section;
    }
    @PutMapping("/professors")
    public Professor updateProfessor(@RequestBody Professor professor){
        professorService.save(professor);
        return professor;
    }

    @PutMapping("/attach/{professorId}/{sectionId}")
    public void attachProfessor(@PathVariable int professorId, @PathVariable int sectionId){
        Professor professor = professorService.findById(professorId);
        Section section = sectionService.findByCRN(sectionId);

        professor.add(section);
        section.setProfessor(professor);

        professorService.save(professor);
        sectionService.save(section);
    }

    // New student          PostMapping /students
    // New professor        PostMapping /professors
    // New Section          PostMapping /sections


    // Find Profess         GetMapping  / professors/{professorId}
    // Find Section         GetMapping  / sections/{sectionId}

    // update student       PutMapping  / students
    // update profess       PutMapping  / professors
    // update sections      PutMapping  / sections

    // attach prof + sec    PutMapping  / professors/{professorId}/{sectionId}
    // attach stu + sec     PutMapping  / students/{studentId}/{CRN}



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFound exc){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProfessorNotFoundException(ProfessorNotFound exc){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSectionNotFoundException(SectionNotFound exc){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus((HttpStatus.NOT_FOUND.value()));
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exc){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
