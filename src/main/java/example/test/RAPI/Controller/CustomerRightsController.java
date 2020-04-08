package example.test.RAPI.Controller;

import example.test.RAPI.Entity.CustomerRights;
import example.test.RAPI.Service.CustomerRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerRightsController {

    @Autowired
    CustomerRightService customerRightService;

    @RequestMapping(method = RequestMethod.GET, value = "/customerrights")
    public List<CustomerRights> getAllCustomerRights() {
        return customerRightService.getAllCustomerRights();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerrights/{customerid}")
    public CustomerRights getCustomerRight(@PathVariable int customerrightid) {
        return customerRightService.getCustomerRightById(customerrightid);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customerrights/delete/{customerrightid}")
    public void deleteCustomerRight(@PathVariable int customerrightid) {
        customerRightService.deleteCustomerRight(customerrightid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customerrights/add")
    public void createCustomerRight(@RequestBody CustomerRights c) {
        customerRightService.createCustomerRight(c);
    }

    //Update Customer(+Rights)
    @RequestMapping(method = RequestMethod.PUT, value = "/customerrights/update")
    public void updateCustomerRight(@RequestBody CustomerRights c) {
        customerRightService.updateCustomerRight(c);
    }

}
