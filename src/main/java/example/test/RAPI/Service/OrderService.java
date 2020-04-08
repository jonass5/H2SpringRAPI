package example.test.RAPI.Service;

import example.test.RAPI.Entity.Order;
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
    private Order_ArtikelService orderArtikelService;

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().forEach(list::add);
        return list;
    }

    public Order getOrderById(int orderid) {
        return orderRepository.findById(orderid).get();
    }

    public void createOrder(Order c) {
        orderRepository.save(c);
    }

    public void updateOrder(Order c) {
//        List<Order_Artikel> list = new ArrayList<>();
//        c.getArtikelList().stream().forEach(list::add);
//
//        for (Order_Artikel oa : list) {
//            if (!orderArtikelService.Order_ArtikelisExisting(c.getOrderid(), oa.getArtikelid().getArtikelid())) {
//                orderArtikelService.createOrder_Artikel(oa);
//            }
//        }

        if (getOrderById(c.getOrderid()) != null) {
            orderRepository.save(c);
        }
    }

    public void deleteOrder(int orderid) {
        orderRepository.deleteById(orderid);
    }


}
