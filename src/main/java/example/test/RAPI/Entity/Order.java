package example.test.RAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BESTELLUNG")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property = "orderClassID")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDERID")
    private int orderid;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @OneToMany(mappedBy = "orderid")
    private Set<Order_Artikel> artikelList;

    public int getOrderid() {
        return orderid;
    }

    public Order() {
    }

    public Order(Set<Order_Artikel> artikelList, Customer customer) {
        this.artikelList = artikelList;
        this.customer = customer;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Set<Order_Artikel> getArtikelList() {
        return artikelList;
    }

    public void setArtikelList(Set<Order_Artikel> artikelList) {
        this.artikelList = artikelList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerid(Customer customer) {
        this.customer = customer;
    }
}
