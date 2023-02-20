package com.uniovi.sdi2223304spring1.services;

import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getProfessors() {
        List<Professor> professor = new ArrayList<Professor>();
        professorRepository.findAll().forEach(professor::add);
        return professor;
    }
    public Professor getProfessor(String dni) {
        return professorRepository.findById(dni).get();
    }
    public void addProfessor(Professor prof) {
        professorRepository.save(prof);
    }
    public void deleteProfessor(String dni) {
        professorRepository.deleteById(dni);
    }

}
