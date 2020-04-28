package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Service.CustomerRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createCustomerRight(Model model, @ModelAttribute CustomerRight customerRight) {
        if (!customerRight.getName().trim().equalsIgnoreCase("")) {
            customerRightService.createCustomerRight(customerRight);
            return "redirect:/api/customerRight";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerRightFrom", customerRight);
        model.addAttribute("appName", appName);
        return "addCustomerRight";
    }

    @PostMapping(value = "/updateCustomerRight")
    public String updateCustomerRight(Model model, @ModelAttribute CustomerRight customerRight) {
        if (customerRightService.isCustomerRightExistById(customerRight.getCustomerrightid()) && !customerRight.getName().trim().equalsIgnoreCase("")) {
            customerRightService.updateCustomerRight(customerRight);
            return "redirect:/api/customerRight";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerRightFrom", customerRight);
        model.addAttribute("appName", appName);
        return "updateCustomerRight";
    }
}
