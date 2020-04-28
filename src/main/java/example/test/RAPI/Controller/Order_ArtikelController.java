package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Entity.Order_Artikel;
import example.test.RAPI.Service.ArtikelService;
import example.test.RAPI.Service.OrderService;
import example.test.RAPI.Service.Order_ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/orderArtikel")
public class Order_ArtikelController {

    @Autowired
    Order_ArtikelService orderArtikelService;

    @Autowired
    ArtikelService artikelService;

    @Autowired
    OrderService orderService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(value = "/addOrderArtikel/{id}")
    public String addOrderArtikel(@PathVariable("id") int id, Model model) {
        Order_Artikel oa = new Order_Artikel(orderService.getOrderById(id), new Artikel(), 0);
        model.addAttribute("orderArtikelForm", oa/*new Order_Artikel(orderService.getOrderById(id), null ,0 )*/);
        model.addAttribute("appName", appName);
        return "addOrderArtikel";
    }

    @GetMapping(value = "/updateOrderArtikel/{oid}-{aid}")
    public String showUpdateForm(@PathVariable("oid") int order_id, @PathVariable("aid") int artikel_id, Model model) {
        model.addAttribute("orderArtikelForm", orderArtikelService.getOrder_ArtikelByOrderId(order_id, artikel_id));
        model.addAttribute("appName", appName);
        return "updateOrderArtikel";
    }

    @PostMapping(value = "addOrderArtikel")
    public String addOrderArtikel(Model model, @ModelAttribute Order_Artikel order_artikel) {
        if (!orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid()) && order_artikel.getMenge() > 0
                && artikelService.isArtikelExistById(order_artikel.getArtikelid().getArtikelid())) {
            orderArtikelService.createOrder_Artikel(order_artikel);
            return "redirect:/api/order/updateOrder/" + order_artikel.getOrderid().getOrderid();
        } else if (orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid())) {
            model.addAttribute("orderArtikelForm", order_artikel);
            model.addAttribute("errorMessage", "OrderArtikel ist bereits vorhanden!");
            model.addAttribute("appName", appName);
            return "addOrderArtikel";
        } else {
            model.addAttribute("orderArtikelForm", order_artikel);
            model.addAttribute("errorMessage", "ArtikelID nicht vorhanden oder Menge zu klein!");
            model.addAttribute("appName", appName);
            return "addOrderArtikel";
        }
    }

    @GetMapping(value = "/deleteOrderArtikel/{oid}-{aid}")
    public String deleteOrderArtikel(@PathVariable("oid") int order_id, @PathVariable("aid") int artikel_id, Model model/*, @ModelAttribute Order_Artikel order_artikel*/) {
        if (orderArtikelService.isOrder_ArtikelExistById(order_id, artikel_id)) {
            orderArtikelService.deleteOrder_Artikel(order_id, artikel_id);
        }

        return "redirect:/api/order/updateOrder/" + order_id;
    }

    @PostMapping(value = "/updateOrderArtikel")
    public String updateOrderArtikel(Model model, @ModelAttribute Order_Artikel order_artikel) {
        if (orderArtikelService.isOrder_ArtikelExistById(order_artikel.getOrderid().getOrderid(), order_artikel.getArtikelid().getArtikelid()) && order_artikel.getMenge() > 0) {
            orderArtikelService.updateOrder_Artikel(order_artikel);
            return "redirect:/api/order/updateOrder/" + order_artikel.getOrderid().getOrderid();
        } else if (order_artikel.getMenge() < 0) {
            model.addAttribute("orderArtikelForm", order_artikel);
            model.addAttribute("errorMessage", "Menge zu klein!");
            model.addAttribute("appName", appName);
            return "updateOrderArtikel";
        }

        model.addAttribute("orderArtikelForm", order_artikel);
        model.addAttribute("errorMessage", "Ein Fehler ist aufgetreten!");
        model.addAttribute("appName", appName);
        return "updateOrderArtikel";
    }
}
