package com.web_dev_494.uGraduate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="section")
public class Section {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "crn_")
	 private int CRN;

	 @Column(name = "class_name")
	 private String className;

	 @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
			               CascadeType.MERGE, CascadeType.REFRESH})
	 @JoinColumn(name="professor_id")
	 private Professor professor;

	@Column(name = "major_id")
	 private int major;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name = "student_section",
			joinColumns = @JoinColumn(name = "crn_"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	@JsonIgnore
	private List<Student> students;

	@Column(name = "section_description")
	private String sectionDescription;

	@Column( name = "section_time")
	private String meetTimes;

	@Column(name="section_day")
	private String meetDays;

	public Section(){

	}

	 public Section(String className){
		 this.className = className;
	 }
	 
	public int getCRN() {
		return CRN;
	}
	public void setCRN(int cRN) {
		CRN = cRN;
	}

	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student){
	 	if(students == null){
	 		students = new ArrayList<>();
		}

	 	students.add(student);
	}

	public void removeStudent(Student student){

		System.out.println("BEFORE... students: " + this.students + " student: " + student);

		students.remove(student);

		System.out.println("AFTER... students: " + this.students + " student: " + student);

	}

	public String getSectionDescription() {
		return sectionDescription;
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}

	@Override
	public String toString() {
		return "Section{" +
				"CRN=" + CRN +
				", className='" + className + '\'' +
				'}';
	}

	public String getMeetTimes() {
		return meetTimes;
	}

	public void setMeetTimes(String meetTimes) {
		this.meetTimes = meetTimes;
	}

	public String getMeetDays() {
		return meetDays;
	}

	public void setMeetDays(String meetDays) {
		this.meetDays = meetDays;
	}
}
