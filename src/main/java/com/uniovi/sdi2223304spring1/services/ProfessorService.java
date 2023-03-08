package com.uniovi.sdi2223304spring1.services;

import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.entities.User;
import com.uniovi.sdi2223304spring1.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Page<Professor> getProfessors(Pageable pageable) {
        Page<Professor> professor = professorRepository.findAll(pageable);
        return professor;
    }
    public Professor getProfessor(String dni) {
        return professorRepository.findById(dni).get();
    }
    public void addProfessor(Professor prof) {
        professorRepository.save(prof);
    }

    public Professor getProfessorByDni(String dni){
        return professorRepository.findByDni(dni);
    }
    public void deleteProfessor(String dni) {
        professorRepository.deleteById(dni);
    }

}
