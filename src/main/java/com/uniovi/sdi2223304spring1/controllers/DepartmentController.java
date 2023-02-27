package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.entities.Department;
import com.uniovi.sdi2223304spring1.entities.Mark;
import com.uniovi.sdi2223304spring1.services.DepartmentService;
import com.uniovi.sdi2223304spring1.services.ProfessorService;
import com.uniovi.sdi2223304spring1.validators.NewDepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private NewDepartmentFormValidator newDepartmentFormValidator;
    @Autowired
    private ProfessorService professorService;

    @RequestMapping("/department/list")
    public String getList(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartments());
        return "department/list";
    }

    @RequestMapping("/department/list/update")
    public String updateList(Model model){
        model.addAttribute("departmentList", departmentService.getDepartments());
        return "department/list::tableDepartment"; // solo retorna el fragmento tableDepartments
    }

    @RequestMapping("/department/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "department/details";
    }

    @RequestMapping(value = "/department/add")
    public String getDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public String setDepartment(@Validated Department department, BindingResult result, Model model) {
        newDepartmentFormValidator.validate(department,result);
        if (result.hasErrors()){
            model.addAttribute("departmentList", departmentService.getDepartments());
            return "department/add";
        }
        departmentService.addDepartment(department);
        return "redirect:/department/list";
    }

    @RequestMapping(value="/department/delete/{id}")
    public String deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return "redirect:/department/list";
    }

    @RequestMapping(value = "/department/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "department/edit";
    }

    @RequestMapping(value="/department/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Department department, @PathVariable Long id){
        Department originalDepartment = departmentService.getDepartmentById(id);
        // modificar solo description y name
        originalDepartment.setDescription(department.getDescription());
        originalDepartment.setName(department.getName());
        departmentService.addDepartment(originalDepartment);
        return "redirect:/department/details/"+id;
    }





}
