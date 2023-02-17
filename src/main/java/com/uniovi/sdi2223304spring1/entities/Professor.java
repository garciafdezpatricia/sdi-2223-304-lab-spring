package com.uniovi.sdi2223304spring1.entities;

public class Professor {

    private String name;
    private String surname;
    private String dni;
    private String category;

    public Professor(){

    }

    public Professor(String name, String surname, String dni, String category) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.category = category;
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
