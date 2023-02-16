package com.uniovi.sdi2223304spring1.repositories;

import com.uniovi.sdi2223304spring1.entities.Mark;
import org.springframework.data.repository.CrudRepository;
public interface MarksRepository extends CrudRepository<Mark, Long> {
}
