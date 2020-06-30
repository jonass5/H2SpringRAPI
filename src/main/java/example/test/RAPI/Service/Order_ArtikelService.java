package example.test.RAPI.Service;

import example.test.RAPI.Entity.IDClass.Order_ArtikelIdClass;
import example.test.RAPI.Entity.Order_Artikel;
import example.test.RAPI.Repository.Order_ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Order_ArtikelService {

//    public List<Order_Artikel> preOrder_ArtikelList;

    @Autowired
    private Order_ArtikelRepository orderArtikelRepository;

    public List<Order_Artikel> getAllOrder_Artikel() {
        List<Order_Artikel> list = new ArrayList<>();
        orderArtikelRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean isOrder_ArtikelExistById(int orderid, int artikelid) {
        return orderArtikelRepository.existsById(new Order_ArtikelIdClass(orderid, artikelid));
    }

    public Order_Artikel getOrder_ArtikelByOrderId(int orderid, int artikelid) {
        return orderArtikelRepository.findById(new Order_ArtikelIdClass(orderid, artikelid)).get();
    }

    public void createOrder_Artikel(Order_Artikel o) {
        orderArtikelRepository.save(o);
    }

    public void updateOrder_Artikel(Order_Artikel o) {
//        if ((o.getArtikelid())  != null) {
        orderArtikelRepository.save(o);
//        }
    }

    public void deleteOrder_Artikel(int orderid, int artikelid) {
        orderArtikelRepository.deleteById(new Order_ArtikelIdClass(orderid, artikelid));
    }

    public boolean Order_ArtikelisExisting(int orderid, int artikelid) {
        return orderArtikelRepository.existsById(new Order_ArtikelIdClass(orderid, artikelid));
    }
}
