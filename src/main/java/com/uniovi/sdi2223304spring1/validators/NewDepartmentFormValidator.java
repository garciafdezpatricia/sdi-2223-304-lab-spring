package com.uniovi.sdi2223304spring1.validators;

import com.uniovi.sdi2223304spring1.entities.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.*;


@Component
public class NewDepartmentFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        if (department.getDescription().length() < 15) {
            errors.rejectValue("description", "Error.addDepartment.description.length");}
    }
}
