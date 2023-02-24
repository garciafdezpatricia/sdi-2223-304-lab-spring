package com.uniovi.sdi2223304spring1.validators;

import com.uniovi.sdi2223304spring1.entities.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class NewMarkFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "Error.empty");
        if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.addMark.score.range");}
        if (mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.addMark.description.length");}
    }
}
