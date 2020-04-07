package example.test.RAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ARTIKEL")
public class Artikel {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int artikelid;

    @JsonIgnore
    public Set<Order_Artikel> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order_Artikel> orderList) {
        this.orderList = orderList;
    }

    @OneToMany(mappedBy = "artikelid")
    private Set<Order_Artikel> orderList;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PREIS")
    private String preis;

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

    public String getPreis() {
        return preis;
    }

    public Artikel() {
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public Artikel(String name, String preis) {
        this.name = name;
        this.preis = preis;
    }
}
