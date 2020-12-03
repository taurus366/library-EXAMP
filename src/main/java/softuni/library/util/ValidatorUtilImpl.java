package softuni.library.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;


public class ValidatorUtilImpl implements ValidatorUtil{

    @Autowired
    private final Validator validator;

    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}
