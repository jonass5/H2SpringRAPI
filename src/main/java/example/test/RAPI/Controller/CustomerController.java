package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Service.CustomerRightService;
import example.test.RAPI.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRightService customerRightService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping
    public String getAllCustomer(Model model) {
        List<Customer> customerlist = customerService.getAllCustomer();
        model.addAttribute("customers", customerlist);
        model.addAttribute("appName", appName);
        return "customerList";
    }

    @GetMapping(value = "/addCustomer")
    public String showAddCustomer(Model model) {
        model.addAttribute("customerForm", new Customer());
        model.addAttribute("appName", appName);
        model.addAttribute("customerrights", customerRightService.getAllCustomerRights());

        return "addCustomer";
    }

    @GetMapping(value = "/updateCustomer/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("customerForm", customerService.getCustomerById(id));
        model.addAttribute("appName", appName);
        model.addAttribute("customerrights", customerRightService.getAllCustomerRights());
        model.addAttribute("selectedright", customerService.getCustomerById(id).getCustomerRights().getCustomerrightid());

        return "updateCustomer";
    }

    @PostMapping(value = "/addCustomer")
    public String createCustomer(Model model, @ModelAttribute @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorResult = new StringBuilder();
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError objectError : result.getAllErrors()) {
                errorResult.append(objectError.getDefaultMessage()).append("<br>");
            }

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("customerForm", customer);
            model.addAttribute("appName", appName);
            model.addAttribute("customerrights", customerRightService.getAllCustomerRights());

            return "addCustomer";
        }

        customer.setCustomerRights(customerRightService.getCustomerRightById(customer.getCustomerRights().getCustomerrightid()));
        customerService.createCustomer(customer);

        return "redirect:/api/customer";
    }

    @GetMapping(value = "/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id, Model model) {
        if (customerService.isCustomerExistById(id)) {
            customerService.deleteCustomer(id);
        } else {
            model.addAttribute("errorMessage", "Löschen nicht möglich");
        }
        return "redirect:/api/customer";
    }

    @PostMapping(value = "/updateCustomer")
    public String updateCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            StringBuilder errorResult = new StringBuilder();
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError objectError : result.getAllErrors()) {
                errorResult.append(objectError.getDefaultMessage()).append("<br>");
            }

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("customerForm", customer);
            model.addAttribute("appName", appName);
            model.addAttribute("customerrights", customerRightService.getAllCustomerRights());
            model.addAttribute("selectedright", customerService.getCustomerById(customer.getCustomerid()).getCustomerRights().getCustomerrightid());

            return "updateCustomer";
        }

        customer.setCustomerRights(customerRightService.getCustomerRightById(customer.getCustomerRights().getCustomerrightid()));
        customerService.updateCustomer(customer);

        return "redirect:/api/customer";
    }
}
