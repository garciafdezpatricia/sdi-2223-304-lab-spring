package com.uniovi.sdi2223304spring1.services;

import com.uniovi.sdi2223304spring1.entities.Department;
import com.uniovi.sdi2223304spring1.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    DepartmentsRepository departmentsRepository;

    public List<Department> getDepartments(){
        List<Department> departments = new ArrayList<Department>();
        departmentsRepository.findAll().forEach(departments::add);
        return departments;
    }

    public Department getDepartmentById(Long id){
        return departmentsRepository.findById(id).get();
    }

    public void addDepartment(Department department){
        departmentsRepository.save(department);
    }

    public void deleteDepartment(Long id){
        departmentsRepository.deleteById(id);
    }
}
