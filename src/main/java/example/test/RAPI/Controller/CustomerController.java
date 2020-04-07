package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/delete/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/add")
    public void createCustomer(@RequestBody Customer c) {
        customerService.createCustomer(c);
    }

    //Update Customer(+Rights)
    @RequestMapping(method = RequestMethod.PUT, value = "/customers/update")
    public void updateCustomer(@RequestBody Customer c) {
        customerService.updateCustomer(c);
    }
}
