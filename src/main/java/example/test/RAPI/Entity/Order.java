package example.test.RAPI.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ARTIKELORDER")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int orderid;

    @ManyToOne
    @JoinColumn(name = "customerid")
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
