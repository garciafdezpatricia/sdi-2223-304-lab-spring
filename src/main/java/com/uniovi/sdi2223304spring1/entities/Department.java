package com.uniovi.sdi2223304spring1.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    // un departamento esta compuesto de muchos profesores
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Professor> professors;

    public Department() {
    }

    public Department(String description, String name){
        super();
        this.description = description;
        this.name = name;
    }

    public List<Professor> getProfessors(){
        return this.professors;
    }

    public void setProfessors(List<Professor> professors){
        this.professors = professors;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name){this.name = name;}

    public String getName() {return this.name;}

}
