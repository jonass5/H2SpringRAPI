package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order_Artikel;
import example.test.RAPI.Service.ArtikelService;
import example.test.RAPI.Service.OrderService;
import example.test.RAPI.Service.Order_ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/orderArtikel")
public class Order_ArtikelController {

    @Autowired
    Order_ArtikelService orderArtikelService;

    @Autowired
    ArtikelService artikelService;

    @Autowired
    OrderService orderService;

//    @GetMapping()
//    public List<Order_Artikel> getAllOrderArtikel() {
//        return orderArtikelService.getAllOrder_Artikel();
//    }

    @GetMapping(value = "/addOrderArtikel")
    public String addOrderArtikel(Model model) {
        model.addAttribute("orderArtikelForm", new Order_Artikel());
        return "addOrderArtikel";
    }

    @GetMapping(value = "/deleteOrderArtikel")
    public String deleteOrderArtikel(Model model) {
        model.addAttribute("orderArtikelForm", new Order_Artikel());
        return "deleteOrderArtikel";
    }

    @GetMapping(value = "/updateOrderArtikel")
    public String updateOrderArtikel(Model model) {
        model.addAttribute("orderArtikelForm", new Order_Artikel());
        return "updateOrderArtikel";
    }

//    @GetMapping(value = "/{orderid}-{artikelid}")
//    public Order_Artikel getOrderArtikel(@PathVariable int orderid, @PathVariable int artikelid) {
//        return orderArtikelService.getOrder_ArtikelByOrderId(orderid, artikelid);
//    }

    @PostMapping(value = "addOrderArtikel")
    public String addOrderArtikel(Model model, @ModelAttribute Order_Artikel order_artikel) {
        if (!orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid()) && order_artikel.getMenge() > 0) {
            orderArtikelService.createOrder_Artikel(order_artikel);
            return "redirect:/api/order";
        } else if (artikelService.isArtikelExistById(order_artikel.getArtikelid().getArtikelid()) && orderService.isOrderExistById(order_artikel.getOrderid().getOrderid())) {
            model.addAttribute("orderArtikelForm", order_artikel);
            model.addAttribute("errorMessage", "OrderArtikel ist bereits vorhanden!");
            return "addOrderArtikel";
        } else {
            model.addAttribute("orderArtikelForm", order_artikel);
            model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
            return "addOrderArtikel";
        }
    }

    @PostMapping(value = "/deleteOrderArtikel")
    public String deleteOrderArtikel(Model model, @ModelAttribute Order_Artikel order_artikel) {
        if (orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid())) {
            orderArtikelService.deleteOrder_Artikel(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid());
            return "redirect:/api/order";
        }

        model.addAttribute("orderArtikelForm", order_artikel);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        return "deleteOrderArtikel";
    }

    @PostMapping(value = "/updateOrderArtikel")
    public String updateOrderArtikel(Model model, @ModelAttribute Order_Artikel order_artikel) {
        if (orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid()) && order_artikel.getMenge() > 0) {
            orderArtikelService.updateOrder_Artikel(order_artikel);
            return "redirect:/api/order";
        }

        model.addAttribute("orderArtikelForm", order_artikel);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        return "updateOrderArtikel";
    }
}
