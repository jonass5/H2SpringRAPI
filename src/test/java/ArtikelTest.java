import example.test.RAPI.Entity.Artikel;
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
public class ArtikelTest {

    private static final String Artikel_API_ROOT = "http://localhost:8080/api/artikel";
    private static final String Artikel_API_ROOT_UPDATE = Artikel_API_ROOT + "/updateArtikel";
    private static final String Artikel_API_ROOT_DELETE = Artikel_API_ROOT + "/deleteArtikel";
    private static final String Artikel_API_ROOT_ADD = Artikel_API_ROOT + "/addArtikel";
    private static final int TESTID = 9999991;

    @Test
    public void whenGetAllArtikel_thenOK() {
        Response response = RestAssured.get(Artikel_API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewArtikel_thenCreated() {
        Artikel artikel = new Artikel();
        artikel.setName("Erdbeere");
        artikel.setArtikelid(TESTID);
        artikel.setPreis(99.99);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(artikel)
                .post(Artikel_API_ROOT_ADD);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedArtikel_thenUpdated() {
        Artikel artikel = new Artikel();
        artikel.setArtikelid(TESTID);
        artikel.setName("Blaubeere");

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(artikel)
                .put(Artikel_API_ROOT_UPDATE);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        assertEquals("Blaubeere", response.jsonPath().get("Artikelname"));
    }

    @Test
    public void whenDeleteCreatedArtikel_thenOk() {
        Artikel artikel = new Artikel();
        artikel.setArtikelid(TESTID);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(artikel)
                .put(Artikel_API_ROOT_DELETE);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}
