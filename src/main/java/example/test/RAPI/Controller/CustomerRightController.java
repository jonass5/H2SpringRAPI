package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Service.CustomerRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/customerRight")
public class CustomerRightController {

    @Autowired
    CustomerRightService customerRightService;

    @GetMapping
    public String getAllCustomerRights(Model model) {
        List<CustomerRight> customerRightList = customerRightService.getAllCustomerRights();
        model.addAttribute("customerRights", customerRightList);
        return "customerRightList";
    }

    @GetMapping(value = "/updateCustomerRight")
    public String updateCustomerRight(Model model) {
        model.addAttribute("customerRightForm", new CustomerRight());
        return "updateCustomerRight";
    }

    @GetMapping(value = "/deleteCustomerRight")
    public String deleteCustomerRight(Model model) {
        model.addAttribute("customerRightForm", new CustomerRight());
        return "deleteCustomerRight";
    }

    @GetMapping(value = "/addCustomerRight")
    public String addCustomerRight(Model model) {
        model.addAttribute("customerRightForm", new CustomerRight());
        return "addCustomerRight";
    }

    @GetMapping(value = "/{customerrightid}")
    public CustomerRight getCustomerRight(@PathVariable int customerrightid) {
        return customerRightService.getCustomerRightById(customerrightid);
    }

    @PostMapping(value = "/deleteCustomerRight")
    public String deleteCustomerRight(Model model, @ModelAttribute CustomerRight customerRight) {
        if (customerRightService.isCustomerRightExistById(customerRight.getCustomerrightid())) {
            customerRightService.deleteCustomerRight(customerRight.getCustomerrightid());
            List<CustomerRight> customerRightList = customerRightService.getAllCustomerRights();
            model.addAttribute("customerRights", customerRightList);
            return "redirect:/api/customerRight";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerRightFrom", customerRight);
        return "deleteCustomerRight";
    }

    @PostMapping(value = "/addCustomerRight")
    public String createCustomerRight(Model model, @ModelAttribute CustomerRight customerRight) {
        if (!customerRight.getName().trim().equalsIgnoreCase("")) {
            customerRightService.createCustomerRight(customerRight);
            List<CustomerRight> customerRightList = customerRightService.getAllCustomerRights();
            model.addAttribute("customerRights", customerRightList);
            return "redirect:/api/customerRight";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerRightFrom", customerRight);
        return "addCustomerRight";
    }

    @PostMapping(value = "/updateCustomerRight")
    public String updateCustomerRight(Model model, @ModelAttribute CustomerRight customerRight) {
        if (customerRightService.isCustomerRightExistById(customerRight.getCustomerrightid()) && !customerRight.getName().trim().equalsIgnoreCase("")) {
            customerRightService.updateCustomerRight(customerRight);
            List<CustomerRight> customerRightList = customerRightService.getAllCustomerRights();
            model.addAttribute("customerRights", customerRightList);
            return "redirect:/api/customerRight";
        }

        model.addAttribute("errorMessage", "ein Fehler ist aufgetreten");
        model.addAttribute("customerRightFrom", customerRight);
        return "updateCustomerRight";
    }
}
