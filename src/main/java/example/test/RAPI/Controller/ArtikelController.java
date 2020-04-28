package example.test.RAPI.Controller;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/artikel")
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping()
    public String getAllArtikel(Model model) {
        List<Artikel> artikelList = artikelService.getAllArtikel();
        model.addAttribute("artikel", artikelList);
        model.addAttribute("appName", appName);
        return "artikelList";
    }

    @GetMapping(value = "/addArtikel")
    public String showAddArtikel(Model model) {
        model.addAttribute("artikelForm", new Artikel());
        model.addAttribute("appName", appName);
        return "addArtikel";
    }

    @GetMapping(value = "/updateArtikel/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("artikelForm", artikelService.getArtikelById(id));
        model.addAttribute("appName", appName);
        return "updateArtikel";
    }

    @GetMapping(value = "/deleteArtikel/{id}")
    public String deleteCustomer(@PathVariable("id") int id, Model model) {
        if (artikelService.isArtikelExistById(id)) {
            artikelService.deleteArtikel(id);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
        }
        return "redirect:/api/artikel";
    }

    @PostMapping(value = "/addArtikel")
    public String createArtikel(Model model, @ModelAttribute Artikel artikel) {
        if (artikel.getName() != null && !artikel.getName().trim().equalsIgnoreCase("") && artikel.getPreis() > 0.0) {
            artikelService.createArtikel(artikel);
            return "redirect:/api/artikel";
        }

        model.addAttribute("errorMessage", "Die Eingabe war falsch");
        model.addAttribute("artikelForm", artikel);
        model.addAttribute("appName", appName);
        return "addArtikel";
    }

    @PostMapping(value = "/updateArtikel")
    public String updateArtikel(Model model, @ModelAttribute Artikel artikel) {
        if (artikelService.isArtikelExistById(artikel.getArtikelid()) && artikel.getName() != null && !artikel.getName().trim().equalsIgnoreCase("") && artikel.getPreis() > 0) {
            artikelService.updateArtikel(artikel);
        } else {
            model.addAttribute("errorMessage", "Die Eingabe war falsch");
            model.addAttribute("artikelForm", artikel);
            model.addAttribute("appName", appName);
            return "updateArtikel";
        }
        return "redirect:/api/artikel";
    }

}
