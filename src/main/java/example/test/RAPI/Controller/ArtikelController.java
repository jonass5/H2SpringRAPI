package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @RequestMapping(method = RequestMethod.GET, value = "/artikel")
    public List<Artikel> getAllArtikel() {
        return artikelService.getAllArtikel();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/artikel/{artikelid}")
    public Artikel getArtikel(@PathVariable int artikelid) {
        return artikelService.getArtikelById(artikelid);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/artikel/delete/{customerid}")
    public void deleteArtikel(@PathVariable int artikelid) {
        artikelService.deleteArtikel(artikelid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/artikel/add")
    public void createArtikel(@RequestBody Artikel a) {
        artikelService.createArtikel(a);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/artikel/update")
    public void updateArtikel(@RequestBody Artikel a) {
        artikelService.updateArtikel(a);
    }

}
