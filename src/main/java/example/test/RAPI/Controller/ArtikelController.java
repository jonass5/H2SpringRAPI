package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artikel")
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @GetMapping()
    public List<Artikel> getAllArtikel() {
        return artikelService.getAllArtikel();
    }

    @GetMapping(value = "/{artikelid}")
    public Artikel getArtikel(@PathVariable int artikelid) {
        return artikelService.getArtikelById(artikelid);
    }

    @DeleteMapping(value = "/{artikelid}")
    public void deleteArtikel(@PathVariable int artikelid) {
        artikelService.deleteArtikel(artikelid);
    }

    @PostMapping()
    public void createArtikel(@RequestBody Artikel a) {
        artikelService.createArtikel(a);
    }

    @PutMapping()
    public void updateArtikel(@RequestBody Artikel a) {
        artikelService.updateArtikel(a);
    }

}
