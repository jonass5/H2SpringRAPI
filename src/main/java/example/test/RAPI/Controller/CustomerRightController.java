package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Service.CustomerRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customerright")
public class CustomerRightController {

    @Autowired
    CustomerRightService customerRightService;

    @GetMapping()
    public List<CustomerRight> getAllCustomerRights() {
        return customerRightService.getAllCustomerRights();
    }

    @GetMapping(value = "/{customerrightid}")
    public CustomerRight getCustomerRight(@PathVariable int customerrightid) {
        return customerRightService.getCustomerRightById(customerrightid);
    }

    @DeleteMapping(value = "/{customerrightid}")
    public void deleteCustomerRight(@PathVariable int customerrightid) {
        customerRightService.deleteCustomerRight(customerrightid);
    }

    @PostMapping()
    public void createCustomerRight(@RequestBody CustomerRight c) {
        customerRightService.createCustomerRight(c);
    }

    @PutMapping()
    public void updateCustomerRight(@RequestBody CustomerRight c) {
        customerRightService.updateCustomerRight(c);
    }

}
