package com.uniovi.sdi2223304spring1.services;
import com.uniovi.sdi2223304spring1.entities.Mark;
import java.util.*;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


@Service
public class MarksService {

    private List<Mark> marksList = new LinkedList<>();
    @PostConstruct
    public void init() {
        marksList.add(new Mark(1L, "Ejercicio 1", 10.0));
        marksList.add(new Mark(2L, "Ejercicio 2", 9.0));
    }
    public List<Mark> getMarks() {
        return marksList;
    }
    public Mark getMark(Long id) {
        return marksList.stream()
                .filter(mark -> mark.getId().equals(id)).findFirst().get();
    }
    public void addMark(Mark mark) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        if (mark.getId() == null) {
            mark.setId(marksList.get(marksList.size() - 1).getId() + 1);
        }
        marksList.add(mark);
    }
    public void deleteMark(Long id) {
        marksList.removeIf(mark -> mark.getId().equals(id));
    }
}
