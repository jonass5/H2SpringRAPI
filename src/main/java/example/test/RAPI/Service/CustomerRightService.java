package example.test.RAPI.Service;

import example.test.RAPI.Entity.CustomerRights;
import example.test.RAPI.Repository.CustomerRightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRightService {

    @Autowired
    private CustomerRightRepository customerRightRepository;

    public List<CustomerRights> getAllCustomerRights() {
        List<CustomerRights> list = new ArrayList<>();
        customerRightRepository.findAll().forEach(list::add);
        return list;
    }

    public CustomerRights getCustomerRightById(int customerRightsid) {
        return customerRightRepository.findById(customerRightsid).get();
    }

    public void createCustomerRight(CustomerRights c) {
        customerRightRepository.save(c);
    }

    public void updateCustomerRight(CustomerRights c) {
        if (getCustomerRightById(c.getId()) != null) {
            customerRightRepository.save(c);
        }
    }

    public void deleteCustomerRight(int customerRightsid) {
        customerRightRepository.deleteById(customerRightsid);
    }
}
