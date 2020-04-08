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

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerid}")
    public Customer getCustomer(@PathVariable int customerid) {
        return customerService.getCustomerById(customerid);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/delete/{customerid}")
    public void deleteCustomer(@PathVariable int customerid) {
        customerService.deleteCustomer(customerid);
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
