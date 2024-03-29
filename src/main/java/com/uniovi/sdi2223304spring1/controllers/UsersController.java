package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.repositories.MarksRepository;
import com.uniovi.sdi2223304spring1.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.uniovi.sdi2223304spring1.services.*;
import com.uniovi.sdi2223304spring1.entities.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;

@Controller
public class UsersController {

    @Autowired
    private MarksService marksService;

    @Autowired
    private RolesService rolesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user,result);
        if (result.hasErrors()){
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, Pageable pageable, @RequestParam(value="", required=false) String searchText) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName(); //en este caso el username es el dni
        User activeUser = usersService.getUserByDni(dni);
        Page<Mark> marks = new PageImpl<Mark>(new ArrayList<Mark>());
        if (searchText != null && !searchText.isEmpty()) {
            marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, activeUser);
        } else {
            marks = marksService.getMarksForUser(pageable, activeUser);
        }
        model.addAttribute("markList", marks.getContent());
        model.addAttribute("page", marks);
        return "home";
    }

    @RequestMapping("/user/list")
    public String getListado(Pageable pageable, Model model, @RequestParam(value="", required = false) String searchText) {
        Page<User> users = new PageImpl<User>(new LinkedList<User>());
        if (searchText != null && !searchText.isEmpty())
            users = usersService.searchUserByNameAndUsername(pageable, searchText);
        else
            users = usersService.getUsers(pageable);
        model.addAttribute("usersList", users.getContent());
        model.addAttribute("page", users);
        return "user/list";
    }
    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {
        model.addAttribute("rolesList", rolesService.getRoles());
        //model.addAttribute("usersList", usersService.getUsers());
        return "user/add";
    }
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }
    @RequestMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@PathVariable Long id, @ModelAttribute User user) {
        User originalUser = usersService.getUser(id);
        // modificar solo DNI, nombre y apellidos
        originalUser.setDni(user.getDni());
        originalUser.setName(user.getName());
        originalUser.setLastName(user.getLastName());
        usersService.addUser(originalUser);
        return "redirect:/user/details/" + id;
    }

    @RequestMapping("/user/list/update")
    public String updateList(Pageable pageable, Model model){
        Page<User> users = usersService.getUsers(pageable);
        model.addAttribute("usersList", users.getContent());
        model.addAttribute("page", users);
        return "user/list::tableUsers"; // solo retorna el fragmento tableMarks
    }
}
