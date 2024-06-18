package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.launchcode.techjobs.persistent.models.Employer;
//import org.launchcode.techjobs.persistent.models.Skill;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

//    @Id
//    @GeneratedValue
//    private int id;

//    private String name;
    @ManyToOne
    @JoinColumn(name = "employer_id")
//    @NotNull(message = "Employer is required")
    private Employer employer;

    @ManyToMany()
//    @JoinColumn(name = "skills_id")
    private List<Skill> skills = new ArrayList<>();


    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
//        this.setSkills(someSkills);
    }

    // Getters and setters.

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Employer getEmployer() {
        return this.employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        System.out.println("setting skills" + skills);
        this.skills = skills;
    }

}
