package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Order_Artikel;
import example.test.RAPI.Service.Order_ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orderartikel")
public class Order_ArtikelController {

    @Autowired
    Order_ArtikelService orderArtikelService;

    @GetMapping()
    public List<Order_Artikel> getAllOrderArtikel() {
        return orderArtikelService.getAllOrder_Artikel();
    }

    @GetMapping(value = "/{orderid}-{artikelid}")
    public Order_Artikel getOrderArtikel(@PathVariable int orderid, @PathVariable int artikelid) {
        return orderArtikelService.getOrder_ArtikelByOrderId(orderid, artikelid);
    }

    @PostMapping()
    public void createOrderArtikel(@RequestBody Order_Artikel o) {
        orderArtikelService.createOrder_Artikel(o);
    }

    @DeleteMapping(value = "/{orderid}-{artikelid}")
    public void deleteOrderArtikel(@PathVariable int orderid, @PathVariable int artikelid) {
        orderArtikelService.deleteOrder_Artikel(orderid, artikelid);
    }

    @PutMapping()
    public void updateOrderArtikel(@RequestBody Order_Artikel a) {
        orderArtikelService.updateOrder_Artikel(a);
    }
}
