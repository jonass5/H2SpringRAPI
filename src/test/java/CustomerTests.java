import example.test.RAPI.Entity.Customer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;

public class CustomerTests {

    /////////////////////
    //TODO ALLE ENTITYS//
    /////////////////////

    private static final String API_ROOT = "http://localhost:8080/api/customer";

    private String createCustomerAsUri(Customer customer) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("customerid");
    }

    @Test
    public void whenCreateNewCustomer_thenCreated() {
        Customer customer = new Customer();
        customer.setName("TestKundeV");
        customer.setNachname("TestKundeN");
        customer.setAge(111);
        customer.setId(1);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .post(API_ROOT);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenGetAllCustomer_thenOK() {
        Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCustomerbyId_thenOK() {
        Response response = RestAssured.get(API_ROOT + "/" + 1);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetNotExistCustomerById_thenInternalServerError() {
        Response response = RestAssured.get(API_ROOT + "/" + -1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedCustomer_thenUpdated() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("TestKundeVNeu");

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customer)
                .put(API_ROOT);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get("http://localhost:8080/api/customer/1");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("TestKundeVNeu", response.jsonPath()
                .get("Vorname"));
    }

    @Test
    public void whenDeleteCreatedCustomer_thenOk() {
        Customer customer = new Customer();
        Response response = RestAssured.delete("http://localhost:8080/api/customer/1");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get("http://localhost:8080/api/customer/1");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
    }

}
