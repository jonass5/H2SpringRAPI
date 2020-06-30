package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.JsonDeserializer.ArtikelJsonDeserializer;
import example.test.RAPI.JsonSerializer.ArtikelJsonSerializer;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ARTIKEL")
@JsonSerialize(using = ArtikelJsonSerializer.class)
@JsonDeserialize(using = ArtikelJsonDeserializer.class)
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIKELID")
    private int artikelid;

    @OneToMany(mappedBy = "artikelid")
    private Set<Order_Artikel> orderList;

    @Column(name = "NAME")
    @NotBlank(message = "{NotBlank.artikel.name}")
    private String name;

    @Column(name = "PREIS")
    @NotNull(message = "{NotNull.artikel.preis}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{DecimalMin.artikel.preis}")
    @Digits(integer = 99, fraction = 2, message = "{Digits.artikel.preis}")
    private double preis;

    public int getArtikelid() {
        return artikelid;
    }

    public void setArtikelid(int customerid) {
        this.artikelid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public Artikel() {
    }

    public Artikel(int artikelid, String name, double preis) {
        this.artikelid = artikelid;
        this.name = name;
        this.preis = preis;
    }

    public Artikel(int artikelid) {
        this.artikelid = artikelid;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Artikel(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    public Set<Order_Artikel> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order_Artikel> orderList) {
        this.orderList = orderList;
    }

}
