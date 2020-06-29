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

//    @Autowired
//    CustomerCreateValidator customerCreateValidator;
//
//    @InitBinder
//    protected void initBinder(WebDataBinder dataBinder) {
//        Object target = dataBinder.getTarget();
//
//        if (target == null) {
//            return;
//        }
//
//        System.out.println("!!! Target=" + target + " !!!");
//
//        if (target.getClass() == Customer.class) {
//            dataBinder.setValidator(customerCreateValidator);
//        }
//    }

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
        return "updateCustomer";
    }

    @PostMapping(value = "/addCustomer")
    public String createCustomer(Model model, @ModelAttribute @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            String errorResult = "";
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError object : result.getAllErrors()) {
                errorResult += object.getDefaultMessage() + "<br>";
            }

            model.addAttribute("errorMessage", errorResult);
            model.addAttribute("customerForm", customer);
            model.addAttribute("appName", appName);

            return "addCustomer";
        }
//        try {
//        if (customer.getName() != null && customer.getName().length() > 0 && customer.getNachname() != null && customer.getNachname().length() > 0 && customer.getAge() > 0) {

//        if (customer.getCustomerRights() != null && customer.getCustomerRights().getCustomerrightid() != 1) {
//            customer.setCustomerRights(null);
//        }
        customer.setCustomerRights(customerRightService.getCustomerRightById(customer.getCustomerRights().getCustomerrightid()));

        customerService.createCustomer(customer);
        return "redirect:/api/customer";
//        }

//        model.addAttribute("errorMessage", "Ein unbekannter Fehler ist aufgetreten");
//        model.addAttribute("customerForm", customer);
//        model.addAttribute("appName", appName);
//        return "addCustomer";

//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Ein unbekannter Fehler ist aufgetreten");
//            model.addAttribute("customerForm", customer);
//            model.addAttribute("appName", appName);
//            return "addCustomer";
//        }
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
        if (customerService.isCustomerExistById(customer.getCustomerid())) {
            if (customer.getCustomerRights().getCustomerrightid() == 0) {
                customer.setCustomerRights(null);
            }
            customerService.updateCustomer(customer);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("customerForm", customer);
            model.addAttribute("appName", appName);
            return "updateCustomer";
        }
        return "redirect:/api/customer";
    }
}
