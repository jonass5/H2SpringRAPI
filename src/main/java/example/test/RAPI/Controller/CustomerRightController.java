package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Service.CustomerRightService;
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
@RequestMapping(value = "/api/customerRight")
public class CustomerRightController {

    @Autowired
    CustomerRightService customerRightService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping
    public String getAllCustomerRights(Model model) {
        List<CustomerRight> customerRightList = customerRightService.getAllCustomerRights();
        model.addAttribute("customerRights", customerRightList);
        model.addAttribute("appName", appName);
        return "customerRightList";
    }

    @GetMapping(value = "/updateCustomerRight/{id}")
    public String updateCustomerRight(@PathVariable("id") int id, Model model) {
        model.addAttribute("customerRightForm", customerRightService.getCustomerRightById(id));
        model.addAttribute("appName", appName);
        return "updateCustomerRight";
    }

    @GetMapping(value = "/deleteCustomerRight/{id}")
    public String deleteCustomerRight(@PathVariable("id") int id, Model model) {
        if (customerRightService.isCustomerRightExistById(id)) {
            customerRightService.deleteCustomerRight(id);
        } else {
            model.addAttribute("errorMessage", "Löschen nicht möglich");
        }
        return "redirect:/api/customerRight";
    }

    @GetMapping(value = "/addCustomerRight")
    public String addCustomerRight(Model model) {
        model.addAttribute("customerRightForm", new CustomerRight());
        model.addAttribute("appName", appName);
        return "addCustomerRight";
    }

    @PostMapping(value = "/addCustomerRight")
    public String createCustomerRight(Model model, @ModelAttribute @Valid CustomerRight customerRight, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorResult = new StringBuilder();
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError objectError : result.getAllErrors()) {
                errorResult.append(objectError.getDefaultMessage()).append("<br>");
            }

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("customerRightForm", new CustomerRight()/*customerRight*/);
            model.addAttribute("appName", appName);

            return "addCustomerRight";
        }

        customerRightService.createCustomerRight(customerRight);

        return "redirect:/api/customerRight";
    }

    @PostMapping(value = "/updateCustomerRight")
    public String updateCustomerRight(Model model, @ModelAttribute @Valid CustomerRight customerRight, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorResult = new StringBuilder();
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError object : result.getAllErrors()) {
                errorResult.append(object.getDefaultMessage()).append("<br>");
            }

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("customerRightForm", customerRight);
            model.addAttribute("appName", appName);

            return "updateCustomerRight";
        }

        customerRightService.updateCustomerRight(customerRight);

        return "redirect:/api/customerRight";
    }
}
