package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.JsonDeserializer.OrderJsonDeserializer;
import example.test.RAPI.JsonSerializer.OrderJsonSerializer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BESTELLUNG")
@JsonSerialize(using = OrderJsonSerializer.class)
@JsonDeserialize(using = OrderJsonDeserializer.class)
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

    public Order(int orderid, Customer customer, Set<Order_Artikel> artikelList) {
        this.orderid = orderid;
        this.customer = customer;
        this.artikelList = artikelList;
    }

    public Order() {
    }

    public Order(int orderid) {
        this.orderid = orderid;
    }

    public Order(Customer customer) {
        this.customer = customer;
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
