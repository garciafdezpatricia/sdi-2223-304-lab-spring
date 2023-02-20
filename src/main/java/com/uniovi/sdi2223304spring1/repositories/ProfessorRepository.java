package com.uniovi.sdi2223304spring1.repositories;

import org.springframework.data.repository.CrudRepository;
import com.uniovi.sdi2223304spring1.entities.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String> {
}
