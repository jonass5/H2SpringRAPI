package example.test.RAPI.Service;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Repository.CustomerRepository;
import example.test.RAPI.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Order_ArtikelService orderArtikelService;

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().forEach(list::add);
        return list;
    }

    public Order getOrderById(int orderid) {
        return orderRepository.findById(orderid).get();
    }

    public boolean isOrderExistById(int orderid) {
        return orderRepository.findById(orderid).isPresent();
    }

    public void createOrder(Order c) {
        orderRepository.save(c);
    }

    public void updateOrder(Order c) {
        if (getOrderById(c.getOrderid()) != null) {
            orderRepository.save(c);
        }
    }

    public void deleteOrder(int orderid) {
        orderRepository.deleteById(orderid);
    }


}
