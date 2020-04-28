package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.CustomerService;
import example.test.RAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.application.name}")
    String appName;

    @GetMapping
    public String getOrders(Model model) {
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("orders", orderList);
        model.addAttribute("appName", appName);
        return "orderList";
    }

    @GetMapping(value = "/addOrder")
    public String addOrder(Model model) {
        model.addAttribute("orderForm", new Order());
        model.addAttribute("appName", appName);
        return "addOrder";
    }

    @GetMapping(value = "/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id, Model model) {
        if (orderService.isOrderExistById(id)) {
            orderService.deleteOrder(id);
        } else {
            model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        }
        return "redirect:/api/order";
    }

    @GetMapping(value = "/updateOrder/{id}")
    public String updateOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("orderForm", orderService.getOrderById(id));
        model.addAttribute("appName", appName);
        return "updateOrder";
    }

    @PostMapping(value = "/addOrder")
    public String addOrder(Model model, @ModelAttribute Order order) {
        if (customerService.isCustomerExistById(order.getCustomer().getCustomerid())) {
            if (customerService.getCustomerById(order.getCustomer().getCustomerid()).getCustomerRights() != null) {
                orderService.createOrder(order);
                return "redirect:/api/order";
            } else {
                model.addAttribute("orderForm", order);
                model.addAttribute("errorMessage", "The customer may not create an order");
                model.addAttribute("appName", appName);
                return "addOrder";
            }
        }
        model.addAttribute("orderForm", order);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        model.addAttribute("appName", appName);
        return "addOrder";
    }

    @PostMapping(value = "/updateOrder")
    public String updateOrder(Model model, @ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/api/order";
    }
}
