package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public List<Order> getOrders() {
        return orderService.getAllOrder();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{orderid}")
    public Order getOrder(@PathVariable int orderid) {
        return orderService.getOrderById(orderid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public void addOrder(@RequestBody Order o) {
        orderService.createOrder(o);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/update")
    public void updateOrder(@RequestBody Order o) {
        orderService.updateOrder(o);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/order/delete/{orderid}")
    public void deleteOrder(@PathVariable int orderid) {
        orderService.deleteOrder(orderid);
    }
}
