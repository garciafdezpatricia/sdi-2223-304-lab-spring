package com.uniovi.sdi2223304spring1.services;

import com.uniovi.sdi2223304spring1.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ProfessorService {

    private List<Professor> professorList = new LinkedList<Professor>();
    @PostConstruct
    public void init() {
        professorList.add(new Professor("Ramon", "Garcia", "774455", "Lengua"));
        professorList.add(new Professor("Maria", "Sanchez", "445566", "Ingles"));
    }
    public List<Professor> getProfessors() {
        return professorList;
    }
    public Professor getProfessorByDni(String dni) {
        return professorList.stream()
                .filter(prof -> prof.getDni().equals(dni)).findFirst().get();
    }
    public void addProfessor(Professor prof) {
        professorList.add(prof);
    }
    public void deleteProfessor(String dni) {
        professorList.removeIf(prof -> prof.getDni().equals(dni));
    }

}
