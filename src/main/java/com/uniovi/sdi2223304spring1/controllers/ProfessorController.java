package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(value="/professor/add", method= RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        return "professor/add";
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor() {
        return "professor/add";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorService.getProfessorByDni(dni));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni){
        professorService.deleteProfessor(dni);
        return "redirect:/professor/list";

    }

    @RequestMapping("/professor/list")
    public String getList(Model model){
        model.addAttribute("professorList", professorService.getProfessors());
        return "professor/list";
    }
}
