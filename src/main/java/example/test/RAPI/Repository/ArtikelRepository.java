package example.test.RAPI.Repository;

import example.test.RAPI.Entity.Artikel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends CrudRepository<Artikel, Integer> {
}
