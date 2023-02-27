package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.entities.Mark;
import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.services.DepartmentService;
import com.uniovi.sdi2223304spring1.services.ProfessorService;
import com.uniovi.sdi2223304spring1.validators.NewProfessorFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private NewProfessorFormValidator newProfessorFormValidator;

    @RequestMapping(value="/professor/add", method= RequestMethod.POST)
    public String setProfessor(@ModelAttribute @Validated Professor professor, BindingResult result, Model model) {
        newProfessorFormValidator.validate(professor,result);
        if (result.hasErrors()){
            model.addAttribute("departmentList", departmentService.getDepartments());
            return "professor/add";
        }
        professorService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("departmentList", departmentService.getDepartments());
        return "professor/add";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorService.getProfessor(dni));
        model.addAttribute("department", professorService.getProfessor(dni).getDepartment());
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

    @RequestMapping(value = "/professor/edit/{dni}")
    public String getEdit(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorService.getProfessor(dni));
        return "professor/edit";
    }

    @RequestMapping(value="/professor/edit/{dni}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor professor, @PathVariable String dni){
        professor.setDni(dni);
        professorService.addProfessor(professor);
        return "redirect:/professor/details/"+dni;
    }
}
