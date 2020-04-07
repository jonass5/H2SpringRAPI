package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public List<Order> getOrders() {
        return orderService.getAllOrder();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public void addOrder(Order o) {
        orderService.createOrder(o);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/update")
    public void getOrders(Order o) {
        orderService.updateOrder(o);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/order/delete/{id}")
    public void getOrders(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

}
