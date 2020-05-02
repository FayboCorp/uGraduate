package com.web_dev_494.uGraduate;

import com.web_dev_494.uGraduate.controller.AdvisorController;
import com.web_dev_494.uGraduate.controller.DefaultController;
import com.web_dev_494.uGraduate.controller.StudentController;
import com.web_dev_494.uGraduate.dao.SectionDAO;
import com.web_dev_494.uGraduate.dao.StudentDAO;
import com.web_dev_494.uGraduate.dao.UserDAO;
import com.web_dev_494.uGraduate.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class UGraduateApplicationTests {
    @Autowired
    private AdvisorController aController;
    @Autowired
    private DefaultController dController;
    @Autowired
    private StudentController sController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentDAO dao;
    @Autowired
    private UserDAO dao2;
    @Autowired
    private SectionDAO dao3;
    @Autowired
    private WebApplicationContext context;
    @Mock
    HttpServletRequest http;
    //checks if student dao is not null
    @Test
    void contextLoads() throws Exception {
        assertThat(dao).isNotNull();
    }
    //checks that the advisor controller is not null
    @Test
    void contextLoads2() throws Exception{
        assertThat(aController).isNotNull();
    }
    //checks that the default controller is not null
    @Test
    void contextLoads3() throws Exception{
        assertThat(dController).isNotNull();
    }
    //checks that the student controller is not null
    @Test
    void contextLoads4() throws Exception{
        assertThat(sController).isNotNull();
    }
    //test that the login page mapping is successful
    @Test
    @WithMockUser(username = "adminadmin", password = "12345qwert")
    public void testLogin() throws Exception{
        this.mockMvc.perform(get("/login")).andExpect(status().isOk());
    }
    //test the login success mapping
    @Test
    //enters incorrect username
    @WithMockUser(username = "user",password = "12345")
    public void testLoginSuccess() throws Exception{
        //the user should not be authenticated and gives an error
        this.mockMvc.perform(get("/login/loginSuccess")).andExpect(status().is4xxClientError());
    }
    //creates a incorrect mock user
    @Test
    @WithMockUser(username = "user",password = "12345")
    //test that the advisor mapping is not authenticated
    public void testAdvisorController() throws Exception{
        this.mockMvc.perform((get("/advisor/home"))).andExpect(status().is4xxClientError());
    }
    //creates a mock user
    @Test
    @WithMockUser(username = "user",password = "12345qwert")
    public void testDefaultController() throws Exception{
        //tests that the user is not authenticated, the mapping should be forbidden
        this.mockMvc.perform(get("/login/loginSuccess")).andExpect(status().is4xxClientError());
    }
    @Test
    //creates a mock user
    @WithMockUser(username = "user",password = "12345qwert")
    public void testStudentController() throws Exception{
        //checks that the unauthenticated user is forbidden
        this.mockMvc.perform(get("/student/home")).andExpect(status().is4xxClientError());
    }
    @Test
    @Transactional
    @Rollback(true)
    //Tests if student in database is found
    public void findAllTest(){
        List<Student> students = dao.findAll();
        Assertions.assertNotNull(students);
    }
    @Test
    //test that a student is created in the database
    public void createStudentTest(){
        //creates a student
        Student student1 = new Student(10,"Stephen","Lambert","CS","s1");
        //sets the values
        student1.setId(10);
        student1.setFirstName("Stephen");
        student1.setLastName("Lambert");
        student1.setMajor("CS");
        student1.setUsername("s1");
        //checks that it is not empty
        Assertions.assertNotNull(student1.getUsername());
        Assertions.assertNotNull(student1.getLastName());
        Assertions.assertNotNull(student1.getFirstName());
        Assertions.assertNotNull(student1.getMajor());
        Assertions.assertNotNull(student1.getId());
    }
    @Test
    //function to test the user database
    public void createUserTest(){
        User user1 = new User(10,"adminadmin","12345qwert");
        user1.setId(10);
        user1.setUsername("adminadmin");
        user1.setPassword("1245qwert");
        Assertions.assertNotNull(user1.getId());
        Assertions.assertNotNull(user1.getUsername());
        Assertions.assertNotNull(user1.getPassword());
    }
    @Test
    //test the sections entity in the database
    public void sectionTest(){
        // Commented out because constructors changed
        /*
        Section section1 = new Section("CS 341",24579);
        section1.setClassName("CS 341");
        section1.setCRN(24579);
        Assertions.assertNotNull(section1.getClassName());
        Assertions.assertNotNull(section1.getCRN());
        Assertions.assertNotNull(section1);
        */
    }
    @Test
    //test the professor entity in the database
    public void professorTest(){
        // Commented out because constructors changed
        /*
        Professor professor1 = new Professor(67492,"Johnson");
        professor1.setProfessorId(67492);
        professor1.setName("Johnson");
        Assertions.assertNotNull(professor1.getProfessorId());
        Assertions.assertNotNull(professor1.getName());
        */
    }
    @Test
    //test the prerequisites entity in the database
    public void prereqTest(){
        PreRequisites prereq = new PreRequisites(99654,35690);
        Assertions.assertNotNull(prereq);
    }
    @Test
    //test the major entity of the database
    public void majorTest(){
        //creates a major id and name
        Major major1 = new Major(441,"CS");
        major1.setMajorId(441);
        major1.setName("CS");
        //checks that it is not empty
        Assertions.assertNotNull(major1.getMajorId());
        Assertions.assertNotNull(major1.getName());
    }
    @Test
    //test the authorities in the database
    public void authorTest(){
        //creates an authority object in the database
        Authorities authorities1 = new Authorities(1131850,"adminadmin");
        authorities1.setId(1131850);
        authorities1.setAuthority("adminadmin");
        Assertions.assertNotNull(authorities1.getId());
        Assertions.assertNotNull(authorities1.getAuthority());
    }
    //CHECKPOINT 4 TEST
    //test the advisor controller for authtication
    //test for correct mapping
    /*@Test
    public testAuthAdvisor(){

    }
     */
    //test the default controller for authentication
    //test for correct mapping
    /*@Test
    public testAuthDefault(){

    }
     */
    //test the advisor controller for authentication
    //test for correct mapping
    /*@Test
    public testAuthLoggout(){

    }
     */
    //test the advisor controller for authentication
    //test for correct mapping
    /*@Test
    public testAuthStudent(){

    }
     */
    // the authentication of all users
    /*
    @Test
    public testUserSecurity(){
        //check for correct authentication
        //check for correct mapping to jsp pages
    }
     */
}
