package com.uniovi.sdi2223304spring1.repositories;

import com.uniovi.sdi2223304spring1.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

    //las funciones findBy<atributo> se dotan de funcionalidad de forma automatica
    User findByDni(String dni);

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE '%' + LOWER(?1) + '%' OR LOWER(u.lastName) LIKE '%' + LOWER(?1) + '%')")
    Page<User> searchByNameAndUsername(Pageable pageable, String searchText);
}
