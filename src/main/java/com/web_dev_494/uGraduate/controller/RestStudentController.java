package com.web_dev_494.uGraduate.controller;

import com.web_dev_494.uGraduate.entity.Section;
import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.entity.User;
import com.web_dev_494.uGraduate.service.MajorService;
import com.web_dev_494.uGraduate.service.SectionService;
import com.web_dev_494.uGraduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/api")
public class RestStudentController {

    private StudentService studentService;
    private MajorService majorService;
    private SectionService sectionService;
    private Student student;

    @Autowired
    public RestStudentController(StudentService studentService,
                                 MajorService majorService, SectionService sectionService){
        this.studentService = studentService;
        this.majorService = majorService;
        this.sectionService = sectionService;
    }

    @RequestMapping("/home")
    public List<Section> home(){
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        //System.out.println(user.getUsername());
        return null;
    }

}
