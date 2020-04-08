package example.test.RAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ARTIKEL")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property = "artikelClassID")
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIKELID")
    private int artikelid;

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

    public Set<Order_Artikel> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order_Artikel> orderList) {
        this.orderList = orderList;
    }

}
