package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Service.CustomerRightService;
import example.test.RAPI.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRightService customerRightService;

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
    public String createCustomer(Model model, @ModelAttribute Customer customer) {
        if (customer.getName() != null && customer.getName().length() > 0 && customer.getNachname() != null && customer.getNachname().length() > 0 && customer.getAge() > 0) {
            if (customer.getCustomerRights() != null && customer.getCustomerRights().getCustomerrightid() != 1) {
                customer.setCustomerRights(null);
            }
            customerService.createCustomer(customer);
            return "redirect:/api/customer";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerForm", customer);
        return "addCustomer";
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(Model model, @ModelAttribute Customer customer) {
        if (customerService.isCustomerExistById(customer.getCustomerid())) {
            customerService.deleteCustomer(customer.getCustomerid());
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("customerForm", new Customer());
            return "deleteCustomer";
        }
        return "redirect:/api/customer";
    }

    //Update Customer(+Rights)
    @PostMapping(value = "/updateCustomer")
    public String updateCustomer(Model model, @ModelAttribute Customer customer) {
        if (customerService.isCustomerExistById(customer.getCustomerid())) {
            if (customer.getCustomerRights().getCustomerrightid() == 0) {
                customer.setCustomerRights(null);
            }
            customerService.updateCustomer(customer);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("customerForm", new Customer());
            return "updateCustomer";
        }
        return "redirect:/api/customer";
    }
}
