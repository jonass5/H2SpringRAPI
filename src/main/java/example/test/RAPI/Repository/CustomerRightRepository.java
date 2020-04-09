package example.test.RAPI.Repository;

import example.test.RAPI.Entity.CustomerRight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRightRepository extends CrudRepository<CustomerRight, Integer> {
}
