package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order;
import example.test.RAPI.Service.CustomerService;
import example.test.RAPI.Service.OrderService;
import example.test.RAPI.Service.Order_ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/api/order")
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    Order_ArtikelService orderArtikelService;

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
//        orderArtikelService.preOrder_ArtikelList = new ArrayList<>();
//
//        Order order = new Order();
//        order.setArtikelList(orderArtikelService.preOrder_ArtikelList);

        model.addAttribute("orderForm", new Order());
        model.addAttribute("appName", appName);
        model.addAttribute("customer", customerService.getAllCustomer());
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
    public String addOrder(Model model, @ModelAttribute @Valid Order order, BindingResult result) {
        StringBuilder errorResult = new StringBuilder();

        if (result.hasErrors()) {
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError objectError : result.getAllErrors()) {
                errorResult.append(objectError.getDefaultMessage()).append("<br>");
            }

//            if (!customerService.isCustomerExistById(order.getCustomer().getCustomerid())) {
//                errorResult.append("Dieser Kunde existiert nicht!");
//            } //else if (!customerService.getCustomerById(order.getCustomer().getCustomerid()).hasRight("order.allow")) {
////                if (!customerService.getCustomerById(order.getCustomer().getCustomerid()).hasRight("order.allow")) {
//                errorResult.append("Dieser Kunde darf keine Bestellung aufgeben!");
////                }
//            }


            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("orderForm", order);
            model.addAttribute("appName", appName);

            return "addOrder";
        } else if (!customerService.getCustomerById(order.getCustomer().getCustomerid()).hasRight("order.allow")) {
            errorResult.append("Dieser Kunde darf keine Bestellung aufgeben!");

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("orderForm", order);
            model.addAttribute("appName", appName);

            return "addOrder";
        }

        orderService.createOrder(order);

        return "redirect:/api/order";
//        } else {
//            model.addAttribute("orderForm", order);
//            model.addAttribute("errorMessage", "The customer may not create an order");
//            model.addAttribute("appName", appName);
//            return "addOrder";
//        }
//    }
//        model.addAttribute("orderForm",order);
//        model.addAttribute("errorMessage","Ein Fehler ist aufgetreten!");
//        model.addAttribute("appName",appName);
//        return"addOrder";
    }

    @PostMapping(value = "/updateOrder")
    public String updateOrder(Model model, @ModelAttribute @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorResult = new StringBuilder();
            System.out.println("Total Errors: " + result.getFieldErrorCount());

            for (ObjectError objectError : result.getAllErrors()) {
                errorResult.append(objectError.getDefaultMessage()).append("<br>");
            }

            if (customerService.isCustomerExistById(order.getCustomer().getCustomerid())) {
                errorResult.append("Dieser Kunde existiert nicht!");
            }

            if (!customerService.getCustomerById(order.getCustomer().getCustomerid()).hasRight("order.allow")) {
                errorResult.append("Dieser Kunde darf keine Bestellung aufgeben!");
            }

            model.addAttribute("errorMessage", errorResult.toString());
            model.addAttribute("orderForm", order);
            model.addAttribute("appName", appName);

            return "addOrder";
        }

        orderService.updateOrder(order);
        return "redirect:/api/order";
    }
}
