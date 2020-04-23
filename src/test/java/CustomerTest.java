import example.test.RAPI.Entity.Customer;
import example.test.RAPI.Main;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class CustomerTest {

    ////////////////////////
    //TODO ALLE CONTROLLER//
    ////////////////////////

    private static final String CUSTOMER_API_ROOT = "http://localhost:8080/api/customer";
    private static final String CUSTOMER_API_ROOT_UPDATE = CUSTOMER_API_ROOT + "/updateCustomer";
    private static final String CUSTOMER_API_ROOT_DELETE = CUSTOMER_API_ROOT + "/deleteCustomer";
    private static final String CUSTOMER_API_ROOT_ADD = CUSTOMER_API_ROOT + "/addCustomer";
    private static final int TESTID = 9999991;

    @Test
    public void whenGetAllCustomer_thenOK() {
        Response response = RestAssured.get(CUSTOMER_API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewCustomer_thenCreated() {
        Customer customer = new Customer();
        customer.setName("Manfred");
        customer.setNachname("Müller");
        customer.setAge(999);
        customer.setCustomerid(TESTID);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .post(CUSTOMER_API_ROOT_ADD);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedCustomer_thenUpdated() {
        Customer customer = new Customer();
        customer.setCustomerid(TESTID);
        customer.setName("Malte");

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .put(CUSTOMER_API_ROOT_UPDATE);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        assertEquals("Malte", response.jsonPath().get("Vorname"));
    }

    @Test
    public void whenDeleteCreatedCustomer_thenOk() {
        Customer customer = new Customer();
        customer.setCustomerid(TESTID);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .put(CUSTOMER_API_ROOT_DELETE);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

}
