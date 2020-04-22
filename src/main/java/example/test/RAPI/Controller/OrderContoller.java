package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public List<Order> getOrders() {
        return orderService.getAllOrder();
    }

    @GetMapping(value = "/{orderid}")
    public Order getOrder(@PathVariable int orderid) {
        return orderService.getOrderById(orderid);
    }

    @PostMapping()
    public void addOrder(@RequestBody Order o) {
        orderService.createOrder(o);
    }

    @PutMapping()
    public void updateOrder(@RequestBody Order o) {
        orderService.updateOrder(o);
    }

    @DeleteMapping(value = "/{orderid}")
    public void deleteOrder(@PathVariable int orderid) {
        orderService.deleteOrder(orderid);
    }
}
