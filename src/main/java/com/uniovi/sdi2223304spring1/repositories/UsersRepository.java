package com.uniovi.sdi2223304spring1.repositories;

import com.uniovi.sdi2223304spring1.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}
