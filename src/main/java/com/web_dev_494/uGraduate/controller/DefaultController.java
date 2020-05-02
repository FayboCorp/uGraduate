package com.web_dev_494.uGraduate.controller;

import com.web_dev_494.uGraduate.entity.Student;
import com.web_dev_494.uGraduate.entity.User;
import com.web_dev_494.uGraduate.security.UserSecurity;
import com.web_dev_494.uGraduate.service.StudentService;
import org.apache.jasper.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// Use this to determine which route the user goes into (Rather than having separate login screens)

@Controller
public class DefaultController {
    StudentService studentService;

    @Autowired
    public DefaultController(StudentService studentService){
        this.studentService = studentService;
    }

    // See UGraduateApplication for defaultsuccessurl
    @RequestMapping(value = {"/loginSuccess", "/"})
    public String loginRoute(HttpServletRequest request, @AuthenticationPrincipal User user,
                             Model model){
        // if user is student, direct to student home, else

        if(request.isUserInRole("STUDENT")){
            model.addAttribute("student", studentService.findByUsername(user.getUsername()));
            return "student_mappings/studentHome";
        }
        else if (request.isUserInRole("ADMIN")){
            return "advisor_mappings/admin-homepage";
        }
        else {
            return "login_failure";
        }
    }
}
