package example.test.RAPI.Service;

import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Repository.CustomerRepository;
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

    public Customer getCustomerById(int customerid) {
        return customerRepository.findById(customerid).get();//.orElseThrow(CustomerNotFoundException::new);
    }

    public boolean isCustomerExistById(int customerid) {
        return customerRepository.findById(customerid).isPresent();//.orElseThrow(CustomerNotFoundException::new);
    }

    public void createCustomer(Customer c) {
        customerRepository.save(c);
    }

    public void updateCustomer(Customer c) {
        if (isCustomerExistById(c.getCustomerid())) {
            customerRepository.save(c);
        }
    }

    public void deleteCustomer(int customerid) {
//        customerRepository.findById(customerid).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(customerid);
    }
}
