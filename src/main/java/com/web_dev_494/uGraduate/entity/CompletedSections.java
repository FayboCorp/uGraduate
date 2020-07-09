package com.web_dev_494.uGraduate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_completed")
public class CompletedSections implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="student_id")
    @JsonIgnore
    private Student student;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="crn_")
    @JsonIgnore
    private Section section;

    @Column(name ="pass_grade")
    private String grade;

    public CompletedSections() {
    }

    public CompletedSections(String grade, Student student, Section section){
        this.grade = grade;
        this.student = student;
        this.section = section;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompletedSections{" +
                "student=" + student +
                ", section=" + section +
                ", grade='" + grade + '\'' +
                '}';
    }
}
