package com.uniovi.sdi2223304spring1.entities;

import javax.persistence.*;

@Entity
public class Professor {

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name ="department_id")
    private Department department;

    @Id
    private String dni;
    private String category;

    public Professor(){

    }

    public Professor(String name, String surname, String dni, String category, Department department) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.category = category;
        this.department = department;
    }

    public void setDepartment(Department department){
        this.department = department;
    }

    public Department getDepartment(){
        return this.department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
