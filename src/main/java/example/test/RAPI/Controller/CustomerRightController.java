package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Service.CustomerRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRightController {

    @Autowired
    CustomerRightService customerRightService;

    @RequestMapping(method = RequestMethod.GET, value = "/customerrights")
    public List<CustomerRight> getAllCustomerRights() {
        return customerRightService.getAllCustomerRights();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerright/{customerrightid}")
    public CustomerRight getCustomerRight(@PathVariable int customerrightid) {
        return customerRightService.getCustomerRightById(customerrightid);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customerright/delete/{customerrightid}")
    public void deleteCustomerRight(@PathVariable int customerrightid) {
        customerRightService.deleteCustomerRight(customerrightid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customerright/add")
    public void createCustomerRight(@RequestBody CustomerRight c) {
        customerRightService.createCustomerRight(c);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customerright/update")
    public void updateCustomerRight(@RequestBody CustomerRight c) {
        customerRightService.updateCustomerRight(c);
    }

}
