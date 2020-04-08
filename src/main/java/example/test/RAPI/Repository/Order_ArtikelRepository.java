package example.test.RAPI.Repository;

import example.test.RAPI.Entity.IDClass.Order_ArtikelIdClass;
import example.test.RAPI.Entity.Order_Artikel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_ArtikelRepository extends CrudRepository<Order_Artikel, Order_ArtikelIdClass> {

}
