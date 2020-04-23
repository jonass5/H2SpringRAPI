package example.test.RAPI.Service;

import example.test.RAPI.Entity.CustomerRight;
import example.test.RAPI.Repository.CustomerRightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRightService {

    @Autowired
    private CustomerRightRepository customerRightRepository;

    public List<CustomerRight> getAllCustomerRights() {
        List<CustomerRight> list = new ArrayList<>();
        customerRightRepository.findAll().forEach(list::add);
        return list;
    }

    public CustomerRight getCustomerRightById(int customerRightsid) {
        return customerRightRepository.findById(customerRightsid).get();
    }

    public boolean isCustomerRightExistById(int customerRightsid) {
        return customerRightRepository.findById(customerRightsid).isPresent();
    }

    public void createCustomerRight(CustomerRight c) {
        customerRightRepository.save(c);
    }

    public void updateCustomerRight(CustomerRight c) {
        if (getCustomerRightById(c.getCustomerrightid()) != null) {
            customerRightRepository.save(c);
        }
    }

    public void deleteCustomerRight(int customerRightsid) {
        customerRightRepository.deleteById(customerRightsid);
    }
}
