package com.uniovi.sdi2223304spring1.services;

import com.uniovi.sdi2223304spring1.entities.User;
import com.uniovi.sdi2223304spring1.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }
    public void addUser(User user) {
        usersRepository.save(user);
    }
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}

