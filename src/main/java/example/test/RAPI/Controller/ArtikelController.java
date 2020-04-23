package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/artikel")
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @GetMapping()
    public String getAllArtikel(Model model) {
        List<Artikel> artikelList = artikelService.getAllArtikel();
        model.addAttribute("artikel", artikelList);

        return "artikelList";
    }

    @GetMapping(value = "/addArtikel")
    public String showAddArtikel(Model model) {
        model.addAttribute("artikelForm", new Artikel());
        return "addArtikel";
    }

    @GetMapping(value = "/deleteArtikel")
    public String showDeleteArtikel(Model model) {
        model.addAttribute("artikelForm", new Artikel());
        return "deleteArtikel";
    }

    @GetMapping(value = "/updateArtikel")
    public String showUpdateArtikel(Model model) {
        model.addAttribute("artikelForm", new Artikel());
        return "updateArtikel";
    }

    @GetMapping(value = "/{artikelid}")
    public Artikel getArtikel(@PathVariable int artikelid) {
        return artikelService.getArtikelById(artikelid);
    }

    @PostMapping(value = "/deleteArtikel")
    public String deleteArtikel(Model model, @ModelAttribute Artikel artikel) {
        if (artikelService.isArtikelExistById(artikel.getArtikelid())) {
            artikelService.deleteArtikel(artikel.getArtikelid());
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("artikelForm", artikel);
            return "deleteArtikel";
        }
        List<Artikel> artikelList = artikelService.getAllArtikel();
        model.addAttribute("artikel", artikelList);
        return "redirect:/api/artikel";
    }

    @PostMapping(value = "/addArtikel")
    public String createArtikel(Model model, @ModelAttribute Artikel artikel) {
        if (artikel.getName() != null && !artikel.getName().trim().equalsIgnoreCase("") && artikel.getPreis() > 0.0) {
            artikelService.createArtikel(artikel);
            List<Artikel> artikelList = artikelService.getAllArtikel();
            model.addAttribute("artikel", artikelList);
            return "redirect:/api/artikel";
        }

        model.addAttribute("errorMessage", "Die Eingabe war falsch");
        model.addAttribute("artikelForm", artikel);
        return "addArtikel";
    }

    @PostMapping(value = "/updateArtikel")
    public String updateArtikel(Model model, @ModelAttribute Artikel artikel) {
        if (artikelService.isArtikelExistById(artikel.getArtikelid()) && artikel.getName() != null && !artikel.getName().trim().equalsIgnoreCase("") && artikel.getPreis() > 0) {
            artikelService.updateArtikel(artikel);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("artikelForm", artikel);
            return "updateArtikel";
        }
        List<Artikel> artikelList = artikelService.getAllArtikel();
        model.addAttribute("artikel", artikelList);
        return "redirect:/api/artikel";
    }

}
