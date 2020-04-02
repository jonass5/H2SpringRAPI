package example.test.RAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().forEach(list::add);
        return list;
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    public void createCustomer(Customer c) {
        customerRepository.save(c);
    }

    public void updateCustomer(Customer c) {
        if (getCustomerById(c.getId()) != null) {
            customerRepository.save(c);
        }
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
