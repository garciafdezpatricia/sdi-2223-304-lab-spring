package com.uniovi.sdi2223304spring1.validators;

import com.uniovi.sdi2223304spring1.entities.Mark;
import com.uniovi.sdi2223304spring1.entities.Professor;
import com.uniovi.sdi2223304spring1.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class NewProfessorFormValidator implements Validator {

    @Autowired
    private ProfessorService professorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (professor.getDni().length() != 9) {
            errors.rejectValue("dni", "Error.addProfessor.dni.length");}
        if (professor.getDni().length() == 9 &&
                !Character.isLetter(professor.getDni().charAt(professor.getDni().length() - 1)))
            errors.rejectValue("dni", "Error.addProfessor.dni.lastLetter");
        if (professorService.getProfessorByDni(professor.getDni()) != null)
            errors.rejectValue("dni", "Error.addProfessor.dni.duplicate");
    }
}
