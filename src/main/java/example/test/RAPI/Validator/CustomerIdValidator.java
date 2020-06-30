package example.test.RAPI.Validator;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerIdValidator implements ConstraintValidator<CustomerIdNotNull, Customer> {

    @Autowired
    CustomerService customerService;

    @Override
    public void initialize(CustomerIdNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = customerService.isCustomerExistById(customer.getCustomerid());
        return isValid;
    }
}
