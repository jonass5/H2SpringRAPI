package example.test.RAPI.Repository;

import example.test.RAPI.Entity.CustomerRights;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRightRepository extends CrudRepository<CustomerRights, Integer> {
}
