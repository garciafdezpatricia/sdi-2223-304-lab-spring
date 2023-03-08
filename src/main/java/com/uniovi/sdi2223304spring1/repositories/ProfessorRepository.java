package com.uniovi.sdi2223304spring1.repositories;

import com.uniovi.sdi2223304spring1.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.uniovi.sdi2223304spring1.entities.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String> {

    Professor findByDni(String dni);

    Page<Professor> findAll(Pageable pageable);
}
