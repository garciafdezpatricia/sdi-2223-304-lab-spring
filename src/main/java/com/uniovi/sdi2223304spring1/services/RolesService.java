package com.uniovi.sdi2223304spring1.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {

    String[] roles = {"ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN"};
    public String[] getRoles() {
        return roles;
    }
}
