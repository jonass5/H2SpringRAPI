package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.CustomerService;
import example.test.RAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/order")
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @GetMapping
    public String getOrders(Model model) {
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("orders", orderList);
        return "orderList";
    }

    @GetMapping(value = "/addOrder")
    public String addOrder(Model model) {
        model.addAttribute("orderForm", new Order());
        return "addOrder";
    }

    @GetMapping(value = "/deleteOrder")
    public String deleteOrder(Model model) {
        model.addAttribute("orderForm", new Order());
        return "deleteOrder";
    }

    @GetMapping(value = "/updateOrder")
    public String updateOrder(Model model) {
        model.addAttribute("orderForm", new Order());
        return "updateOrder";
    }

    @GetMapping(value = "/{orderid}")
    public Order getOrder(@PathVariable int orderid) {
        return orderService.getOrderById(orderid);
    }

    @PostMapping(value = "/addOrder")
    public String addOrder(Model model, @ModelAttribute Order order) {
        if (customerService.isCustomerExistById(order.getCustomer().getCustomerid())) {
            if (customerService.getCustomerById(order.getCustomer().getCustomerid()).getCustomerRights() != null) {
                orderService.createOrder(order);
                List<Order> orderList = orderService.getAllOrder();
                model.addAttribute("orders", orderList);
                return "redirect:/api/order";
            } else {
                model.addAttribute("orderForm", order);
                model.addAttribute("errorMessage", "The customer may not create an order");
                return "addOrder";
            }
        }
        model.addAttribute("orderForm", order);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        return "addOrder";
    }

    @PostMapping(value = "/updateOrder")
    public String updateOrder(Model model, @ModelAttribute Order order) {
        orderService.updateOrder(order);
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("orders", orderList);
        return "redirect:/api/order";
    }

    @PostMapping(value = "/deleteOrder")
    public String deleteOrder(Model model, @ModelAttribute Order order) {
        if (orderService.isOrderExistById(order.getOrderid())) {
            orderService.deleteOrder(order.getOrderid());
            List<Order> orderList = orderService.getAllOrder();
            model.addAttribute("orders", orderList);
            return "redirect:/api/order";
        }

        model.addAttribute("orderForm", order);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        return "deleteOrder";
    }
}
