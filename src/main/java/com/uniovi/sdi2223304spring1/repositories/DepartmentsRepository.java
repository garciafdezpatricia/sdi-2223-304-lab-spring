package com.uniovi.sdi2223304spring1.repositories;

import com.uniovi.sdi2223304spring1.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends CrudRepository<Department, Long> {
}
