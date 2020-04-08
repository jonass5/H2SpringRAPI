package example.test.RAPI.Service;

import example.test.RAPI.Entity.Artikel;
import example.test.RAPI.Repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtikelService {

    @Autowired
    private ArtikelRepository artikelRepository;

    public List<Artikel> getAllArtikel() {
        List<Artikel> list = new ArrayList<>();
        artikelRepository.findAll().forEach(list::add);
        return list;
    }

    public Artikel getArtikelById(int artikelid) {
        return artikelRepository.findById(artikelid).get();
    }

    public void createArtikel(Artikel a) {
        artikelRepository.save(a);
    }

    public void updateArtikel(Artikel a) {
        if (getArtikelById(a.getArtikelid()) != null) {
            artikelRepository.save(a);
        }
    }

    public void deleteArtikel(int artikelid) {
        artikelRepository.deleteById(artikelid);
    }

}
