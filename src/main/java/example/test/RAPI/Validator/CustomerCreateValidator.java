package example.test.RAPI.Validator;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerCreateValidator implements Validator {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Customer.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customer.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nachname", "NotEmpty.customer.nachname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.customer.age");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerRights.customerrightid", "NotEmpty.customer.customerRights");

        if (!errors.hasFieldErrors("age")) {
            if (customer.getAge() > 0) {
                errors.rejectValue("age", "NotZero.customer.age");
            }
        }

        if (!errors.hasErrors()) {
            if (!(customer.getCustomerRights().getCustomerrightid() == 1 && customer.getCustomerRights().getCustomerrightid() == 0)) {
                errors.rejectValue("confirmPassword", "Match.customer.customerRights");
            }
        }

    }
}
