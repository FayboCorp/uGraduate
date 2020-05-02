package com.web_dev_494.uGraduate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="professor")
public class Professor {

     @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "professor_id")
	 private int professorId;
	 
	 @Column(name = "professor_name")
	 private String name;

	 // TODO: Hibernate isn't joining professor with section. Fix it
	 @OneToMany(mappedBy = "professor",
	 			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						   CascadeType.DETACH, CascadeType.REFRESH})
	 @JsonIgnore
	 private List<Section> sections;

	 //constructors
	 public Professor(){};

	 public Professor(String name){
		 this.name = name;
	 }
	 
	 //getters and setters
	 public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorID) {
		this.professorId = professorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public void add(Section tempSection){
	 	if(sections == null){
	 		sections = new ArrayList<>();
		}
		System.out.println("Adding " + tempSection.getClassName());

	 	sections.add(tempSection);
		tempSection.setProfessor(this);
	}


	@Override
	public String toString() {
		return "Professor{" +
				"professorId=" + professorId +
				", name='" + name + '\'' +
				'}';
	}


}
