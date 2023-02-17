package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(value="/professor/add", method= RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        return "Ok";
    }
    @RequestMapping("/professor/details/{dni}")
    public String getDetail(@PathVariable String dni) {
        return professorService.getProfessorByDni(dni).toString();
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni){
        professorService.deleteProfessor(dni);
        return "Ok";
    }

    @RequestMapping("/professor/list")
    public String getList(){
        return professorService.getProfessors().toString();
    }
}
