package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public String getAllCustomer(Model model) {

        List<Customer> customerlist = customerService.getAllCustomer();
        model.addAttribute("customers", customerlist);

        return "customerList";
    }

    @GetMapping(value = "/addCustomer")
    public String showAddCustomer(Model model) {
        model.addAttribute("customerForm", new Customer());
        return "addCustomer";
    }

    @GetMapping(value = "/deleteCustomer")
    public String showDeleteCustomer(Model model) {
        model.addAttribute("customerForm", new Customer());
        return "deleteCustomer";
    }

    @GetMapping(value = "/updateCustomer")
    public String showUpdateCustomer(Model model) {
        model.addAttribute("customerForm", new Customer());
        return "updateCustomer";
    }

    @PostMapping(value = "/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(Model model, @ModelAttribute Customer customer) {
        String name = customer.getName();
        String nachname = customer.getNachname();
        int age = customer.getAge();
//        CustomerRight customerRight = customer.getCustomerRights();

        if (name != null && name.length() > 0 && nachname != null && nachname.length() > 0 && age > 0 /*&& (customerRight.getId() == 1 || customerRight == null)*/) {
            Customer newCustomer = new Customer(name, nachname, age/*, customerRight*/);
            customerService.createCustomer(newCustomer);

            List<Customer> customerlist = customerService.getAllCustomer();
            model.addAttribute("customers", customerlist);
            return "customerList";
        }

        model.addAttribute("resultMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerForm", customer);
        return "addCustomer";
    }

    @GetMapping("/{customerid}")
    public Customer getCustomer(@PathVariable int customerid) {
        return customerService.getCustomerById(customerid);
    }

    @PostMapping("/deleteCustomer")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(Model model, @ModelAttribute Customer customer) {
        if (customerService.isCustomerExistById(customer.getId())) {
            customerService.deleteCustomer(customer.getId());
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("customerForm", new Customer());
            return "deleteCustomer";
        }
        List<Customer> customerlist = customerService.getAllCustomer();
        model.addAttribute("customers", customerlist);
        return "customerList";
    }

    //Update Customer(+Rights)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/updateCustomer")
    public String updateCustomer(Model model, @ModelAttribute Customer customer) {
        if (customerService.isCustomerExistById(customer.getId())) {
            customerService.updateCustomer(customer);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("customerForm", new Customer());
            return "deleteCustomer";
        }
        List<Customer> customerlist = customerService.getAllCustomer();
        model.addAttribute("customers", customerlist);
        return "customerList";
    }
}
