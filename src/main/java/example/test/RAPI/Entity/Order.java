package example.test.RAPI.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.test.RAPI.JsonDeserializer.OrderJsonDeserializer;
import example.test.RAPI.JsonSerializer.OrderJsonSerializer;
import example.test.RAPI.Validator.CustomerIdNotNull;

import javax.persistence.*;
import java.util.List;

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
    @CustomerIdNotNull(message = "{CustomerIdNotNull.order.customer}")
    private Customer customer;

    @OneToMany(mappedBy = "orderid")
//    @CustomerIdNotNull(message = "{IdNotNull.artikelList.artikelList}")
    private List<Order_Artikel> artikelList;

    public int getOrderid() {
        return orderid;
    }

    public Order(int orderid, Customer customer, List<Order_Artikel> artikelList) {
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

    public Order(List<Order_Artikel> artikelList, Customer customer) {
        this.artikelList = artikelList;
        this.customer = customer;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public List<Order_Artikel> getArtikelList() {
        return artikelList;
    }

    public void setArtikelList(List<Order_Artikel> artikelList) {
        this.artikelList = artikelList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
