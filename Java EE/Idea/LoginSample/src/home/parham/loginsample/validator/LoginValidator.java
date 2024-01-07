package home.parham.loginsample.validator;

import home.parham.loginsample.beans.LoginBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by parham on 9/25/14.
 */
public class LoginValidator {
    Validator validator;

    ValidatorFactory validatorFactory;

    public void logValidation(LoginBean loginBean) {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        Set<ConstraintViolation<LoginBean>> violationSet = validator.validate(loginBean);
        System.out.println("INFO: Validator created");
        for (ConstraintViolation<LoginBean> violation : violationSet) {
            System.out.println(violation.getMessage());
        }
    }

}
