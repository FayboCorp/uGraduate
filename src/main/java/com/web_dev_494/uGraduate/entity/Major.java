package com.web_dev_494.uGraduate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private int majorId;
	  
    @Column(name = "major_name")
    private String name;

    @OneToMany(mappedBy = "major",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Section> sections;

    //constructors
    public Major() {}

    public Major(int majorId, String name){
        this.majorId = majorId;
        this.name = name;
    }



    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getMajorId() {
        return majorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String majorName) {
        this.name = majorName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void add(Section section){
        if(this.sections == null){
            this.sections = new ArrayList<>();
        }

        this.sections.add(section);
        //section.setMajor(this);
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", name='" + name + '\'' +
                '}';
    }
}
